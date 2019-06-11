package BIO.client;

import java.io.IOException;
import java.net.Socket;

/**
 * @author: zhun.huang
 * @create: 2018-05-07 下午11:03
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Client {

    public static void main(String[] args) throws IOException {
        while (true) {
            Socket socket = new Socket("localhost", 8088);
            socket.getOutputStream().write("query current time".getBytes());
            socket.getOutputStream().flush();
            socket.getOutputStream().write("\n".getBytes());
            byte[] response = new byte[2048];
            socket.getInputStream().read(response);
            System.out.println(new String(response));
            System.in.read();
            socket.getOutputStream().close();
        }
    }
}
