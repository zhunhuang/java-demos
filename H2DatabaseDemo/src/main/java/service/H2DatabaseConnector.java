package service;

import org.h2.tools.Server;

import java.sql.*;

/**
 * @author: zhun.huang
 * @create: 2018-03-30 下午3:39
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class H2DatabaseConnector {

    private static Server server;

    private static String port = "8082";
    private static String sourceURL1 = "jdbc:h2:mem:h2db";

    private static String user = "sa";
    private static String password = "";

    private static Connection connection = null;

    public static void startServer() {
        try {
            System.out.println("正在启动h2...");
            server = Server.createTcpServer(new String[] { "-tcpPort", port }).start();
        } catch (SQLException e) {
            System.out.println("启动h2出错：" + e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void stopServer() {
        if (server != null) {
            System.out.println("正在关闭h2...");
            server.stop();
            System.out.println("关闭成功.");
        }
    }

    public static Connection getConnection() {
        if (server == null) {
            startServer();
            connection = createConnection();
        }
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(sourceURL1,user, password);
        } catch (Exception e) {
            System.out.println("创建h2连接失败" +e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
