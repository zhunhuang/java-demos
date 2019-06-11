package BIO.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhun.huang
 * @create: 2018-05-07 下午11:07
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class BIOServer {

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("started server...");
        while (true) {
            executor.execute(new TimeHandler(server.accept()));
        }
    }

    public static class TimeHandler implements Runnable{

        private Socket socket;

        public TimeHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("received request...");
            byte[] request = new byte[2048];
            try {
                // 模拟服务器处理时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                int read = socket.getInputStream().read(request);
                String substring = new String(request).substring(0, read);
                if ("query current time".equals(substring)) {
                    SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd");
                    String response = sb.format(new Date());
                    System.out.println("response: " + response);
                    socket.getOutputStream().write(response.getBytes());
                    socket.getOutputStream().flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
