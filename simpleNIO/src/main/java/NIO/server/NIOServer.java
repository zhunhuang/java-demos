package NIO.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: zhun.huang
 * @create: 2018-05-07 下午11:27
 * @email: nolan.zhun@gmail.com
 * @description: 有关socket的事:https://www.cnblogs.com/straight/articles/7660889.html
 */
public class NIOServer {
    public static Executor selectExecutor = Executors.newFixedThreadPool(1, Executors.defaultThreadFactory());
    public static Executor ioExecutor = Executors.newFixedThreadPool(200, Executors.defaultThreadFactory());

    public static LongAdder longAdder = new LongAdder();

    public static long startTime;

    static {
        Executors.newScheduledThreadPool(1)
                .scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("qps: " + longAdder.longValue() * 10000 / (System.currentTimeMillis() - startTime));
                    }
                }, 2000,1000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws IOException {
        startTime = System.currentTimeMillis();
        selectExecutor.execute(new MultiplexerTimeServer());
        System.in.read();
    }

    public static class MultiplexerTimeServer implements Runnable {

        private Selector selector;

        private ServerSocketChannel serverSocketChannel;

        private volatile boolean stop;

        public MultiplexerTimeServer() {
            try {
                // 开启一个新的selector, 这个selector上面可以绑定各种需要关系的事件
                // 在linux操作系统中, 可能选择 epoll, poll, select
                // 默认是epoll, 底层调用上, 返回参数是两个文件描述符.
                // Pipe管道的读端的文件描述符,Pipe管道的写端的文件描述符
                // 还会调用epollCtl()方法
                // https://blog.csdn.net/u010853261/article/details/53464475
                selector = Selector.open();
                // 开启一个socket监听通道, new serverSocketChannel
                serverSocketChannel = ServerSocketChannel.open();
                // 将这个通道设置为非阻塞的, 即:
                serverSocketChannel.configureBlocking(false);
                /* 将这个通道绑定到对应的端口上去.
                服务器端需要调用bind()函数把一个地址族中的特定地址赋给socket
                客户端不会调用，而是在connect()时由系统随机生成一个
                 backlog :对连接的请求的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接
                */
                serverSocketChannel.socket().bind(new InetSocketAddress(8088), 1024);
                //如果作为一个服务器，在调用socket()、bind()之后就会调用listen()来监听这个socket，如果客户端这时调用connect()发出连接请求，服务器端就会接收到这个请求
                // 看serverSocket的bind()方法实现, 实际上就已经调用了listen()方法.


                // 向selector注册关心的事件
                /* 四类事件
                SelectionKey.OP_ACCEPT —— 接收连接继续事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
                SelectionKey.OP_CONNECT —— 连接就绪事件，表示客户与服务器的连接已经建立成功
                SelectionKey.OP_READ —— 读就绪事件，表示通道中已经有了可读的数据，可以执行读操作了（通道目前有数据，可以进行读操作了）
                SelectionKey.OP_WRITE —— 写就绪事件，表示已经可以向通道写数据了（通道目前可以用于写操作）
                */
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("server started...");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    // 会被阻塞到至少有一个channel注册的事件发生了.
                    // 调用epollWait()方法.
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        try {
                            handleInput(selectionKey);
                        } catch (Exception e) {
                            // 对异常情况进行处理
                            if (selectionKey != null) {
                                selectionKey.cancel();
                                if (selectionKey.channel() != null) {
                                    selectionKey.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()) {
                // 表明当前是收到连接建立的请求, channel 是ServerSocketChannel
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);

                }
                if (key.isReadable()) {
                    // 放到异步IO线程池中去处理。
                    ioExecutor.execute(new IoHandler(key));
                }
            }
        }
    }

    public static class IoHandler implements Runnable {
        private final SelectionKey key;

        public IoHandler(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(2048);
            int readBytes = 0;
            try {
                readBytes = sc.read(readBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (readBytes > 0) {
                // 将这个buffer的游标position设置为起始位置0, 这样就能读取数据了
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                if ("query current time".equals(new String(bytes))) {
                    SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd");
                    String response = sb.format(new Date());
                    try {
                        doWrite(sc, response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (readBytes < 0) {
                // 对链路关闭
                key.cancel();
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 读到0字节, 忽略
            }
        }

        private void doWrite(SocketChannel sc, String response) throws IOException {
            if (response != null) {
                ByteBuffer writeBuffer = ByteBuffer.allocate(response.getBytes().length);
                writeBuffer.put(response.getBytes());
                writeBuffer.flip();
                sc.write(writeBuffer);
                longAdder.increment();
            }
        }
    }
}
