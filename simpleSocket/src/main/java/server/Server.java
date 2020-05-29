package server;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 下午5:33
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Server {

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("server started...");
        while (true) {
            // 每次新建一个线程处理
            executor.execute(new Handler(server.accept()));
        }
    }

    public static class Handler implements Runnable {

        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                char[] input = new char[4096];
                int size = new InputStreamReader(socket.getInputStream()).read(input);
                byte[] output = process(new String(input, 0, size));
                socket.getOutputStream().write(output);
                socket.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.getOutputStream().close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private byte[] process(java.lang.String input) {
            System.out.println("收到请求：\n" + input);
            String response = null;
            try {
                String body = "hello";
                response = "HTTP/1.1 200 OK\n"
                        + "Content-Type: text/html; charset=UTF-8\n"
                        + "Server: socketMockServer/1.0\n"
                        + "\n"
                        + body;
                return response.getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("返回响应：\n" + response);
            }
            return "response: null".getBytes();
        }
    }
}
