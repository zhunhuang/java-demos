import service.H2DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: zhun.huang
 * @create: 2018-03-30 下午3:35
 * @email: nolan.zhun@gmail.com
 * @description: http://www.importnew.com/17924.html
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = H2DatabaseConnector.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE admin_user(`name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',`password` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '密码');");
            statement.execute("INSERT INTO `admin_user` VALUES ('admin','123456')");
            ResultSet resultSet = statement.executeQuery("select * from `admin_user` limit 1");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            H2DatabaseConnector.closeConnection();
        }
    }
}
