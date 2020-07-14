package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 下午3:51
 * @email: nolan.zhun@gmail.com
 * @description: 新增线程池
 */
public class RPCFramwork2 {

    private static ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    static {
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            System.out.println("活跃线程: " + executorService.getActiveCount());
        }, 20, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * 暴露服务 = 开启一个异步线程，通过serverSocket监听端口
     *
     * @param service 服务实现类
     * @param port    服务端口
     * @throws Exception
     */
    public static void export(final Object service, final int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service is null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        ServerSocket server = new ServerSocket(port);

        while (!Thread.interrupted()) {
            executorService.execute(new Handler(server.accept(), service));
        }
    }

    /**
     * 获取服务 = 返回一个实现该服务接口的代理类，代理类内部通过socket发起远程通信
     *
     * @param interfaceClass 接口类型
     * @param host           服务器主机名
     * @param port           服务器端口
     * @param <T>            接口泛型
     * @return 远程服务
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class is null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("host is null");
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Get remote service [" + interfaceClass.getName() + "] from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, (proxy, method, args) -> {
            try (Socket socket = new Socket(host, port)) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                    objectOutputStream.writeUTF(method.getName());
                    objectOutputStream.writeObject(method.getParameterTypes());
                    objectOutputStream.writeObject(args);
                    try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                        Object result = inputStream.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;
                    }
                }
            }
        });
    }

    public static class Handler implements Runnable {
        final Socket socket;
        final Object service;

        public Handler(Socket socket, Object service) {
            this.socket = socket;
            this.service = service;
        }
        @Override
        public void run() {
            try {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    try {
                        String methodName = inputStream.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                        Object[] arguments = (Object[]) inputStream.readObject();
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        try {
                            Method method = service.getClass().getMethod(methodName, parameterTypes);
                            Object result = method.invoke(service, arguments);
                            System.out.println("method " + methodName
                                    + ", invoked, param: " + (arguments == null ? "null" : arguments)
                                    + ", return value:" + (result == null ? "null" : result));
                            outputStream.writeObject(result);
                        } catch (Throwable e) {
                            outputStream.writeObject(e);
                            e.printStackTrace();
                        } finally {
                            outputStream.close();
                        }
                    } finally {
                        inputStream.close();
                    }
                } finally {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
