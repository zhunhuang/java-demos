package NIO.client;

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
            // https://blog.csdn.net/zhujun_xiaoxin/article/details/78496728
            //https://blog.csdn.net/beinlife/article/details/52678567
            // 关于flush的作用. 只有是bufferedOutputStream时,才会有用

            socket.getOutputStream().flush();

            byte[] response = new byte[2048];
            socket.getInputStream().read(response);
            System.out.println(new String(response));
            System.in.read();
        }
    }
}
