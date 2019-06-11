package BIO.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: zhun.huang
 * @create: 2018-05-10 下午5:40
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket socket = server.accept();
            new Thread(new Timer(socket)).start();
        }
    }
    public static class Timer implements Runnable{
        Socket socket;

        public Timer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                String request = "";
                System.out.println("收到请求");
                char[] chars = new char[1024];
                int len;
                while ((len = bufferedReader.read(chars))!=-1) {
                    request = request.concat(new String(chars,0,len));
                    System.out.println(request);
                }
                printWriter.print("hhah");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
