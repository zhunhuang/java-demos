package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 下午5:43
 * @email: nolan.zhun@gmail.com
 * @description: 可通过:netstat -anp| grep "12321" 查看网络链接情况. 创建了两个tcp连接
 */
public class Client {

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1", 12321);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello, remote".getBytes());
            byte[] output = new byte[1024];
            socket.getInputStream().read(output);
            System.out.println(new String(output));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        }
    }
}
