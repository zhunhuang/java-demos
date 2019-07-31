package NIO.client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: zhun.huang
 * @create: 2018-05-07 下午11:03
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Client {

    public static Executor mockClients = Executors.newFixedThreadPool(200);

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            mockClients.execute(new CRunable());
        }
        System.in.read();
    }

    public static class CRunable implements Runnable {

        @Override
        public void run() {
            Socket socket = null;
            try {
                socket = new Socket("localhost", 8088);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    socket.getOutputStream().write("query current time".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // https://blog.csdn.net/zhujun_xiaoxin/article/details/78496728
                //https://blog.csdn.net/beinlife/article/details/52678567
                // 关于flush的作用. 只有是bufferedOutputStream时,才会有用

                try {
                    socket.getOutputStream().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                byte[] response = new byte[2048];
                int length = 0;
                try {
                    length = socket.getInputStream().read(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(response, 0, length));
            }
        }
    }
}
