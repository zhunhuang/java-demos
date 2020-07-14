package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 上午10:44
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class RPCFramwork {

    /**
     * 暴露服务 = 开启一个异步线程，通过serverSocket监听端口
     *
     * @param service 服务实现类
     * @param port    服务端口
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance is null");
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port" + port);
        }
        System.out.println("Export service:[ " + service.getClass().getName() + "] on port " + port);
        ServerSocket server = new ServerSocket(port);
        for (; ; ) {
            Socket socket = server.accept();
            try {
                new Thread(() -> {
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
                                            + ", invoked, param: " + (arguments == null ? "null" : arguments.toString())
                                            + ", return value:" + (result == null ? "null" : result.toString()));
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
                }).start();
            } catch (Throwable e) {
                System.out.println("socket connection fail" + e);
            }
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
            throw new IllegalArgumentException("The" + interfaceClass.getName() + " must be interface class");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("host is null");
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Get remote service [" + interfaceClass.getName() + "] from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, (proxy, method, args) -> {
            Socket socket = new Socket(host, port);
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                try {
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
                } finally {
                    objectOutputStream.close();
                }
            } finally {
                socket.close();
            }
        });
    }
}
