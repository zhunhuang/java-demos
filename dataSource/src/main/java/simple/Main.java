package simple;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

/**
 * description:
 * create: 2018-07-21
 *
 * @author zhun.huang
 */
public class Main {

    static BasicDBSource basicDBSource = null;
    static Random random = new Random(System.currentTimeMillis());

    static {
        try {
            basicDBSource = new BasicDBSource();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("构建连接池失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        prepare();
        int nums = 1000;
        for (int i = 0; i < 10; i++) {

            // 单一连接
            executeWithOneConnection(nums);
            // 线程池
            executeWithPool(nums);
            //druid线程池
            executeWithDruidPool(nums);
            // 非线程池
            executeWithoutPool(nums);
        }
    }

    public static void prepare() {
        // 去掉首次获取连接较慢的情况
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(basicDBSource.getUrl(), basicDBSource.getUser(), basicDBSource.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void executeWithDruidPool(int nums) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums; i++) {
            Connection connection = null;
            try {
                connection = DruidDBSource.dataSource.getConnection();
                exeCuteQuery(connection);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        System.out.println("druid线程池执行" + nums + "条sql查询sql耗时: " + (System.currentTimeMillis() - start));
    }

    public static void executeWithOneConnection(int nums) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(basicDBSource.getUrl(), basicDBSource.getUser(), basicDBSource.getPassword());
            long start = System.currentTimeMillis();
            for (int i = 0; i < nums; i++) {
                exeCuteQuery(connection);
            }
            System.out.println("复用连接执行" + nums + "条sql查询耗时: " + (System.currentTimeMillis() - start));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void executeWithPool(int nums) throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums; i++) {
            Connection connection = null;
            try {
                connection = basicDBSource.getConnection();
                exeCuteQuery(connection);
            } finally {
                basicDBSource.closeConnection(connection);
            }
        }
        System.out.println("线程池执行" + nums + "条sql查询sql耗时: " + (System.currentTimeMillis() - start));

    }

    public static void executeWithoutPool(int nums) throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(basicDBSource.getUrl(), basicDBSource.getUser(), basicDBSource.getPassword());
                exeCuteQuery(connection);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        System.out.println("非线程池执行" + nums + "条sql耗时: " + (System.currentTimeMillis() - start));
    }

    public static ResultSet exeCuteQuery(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_name=?");
        // 每次查询随机用户名,避免mysql的缓存影响结果
        preparedStatement.setString(1, String.valueOf(random.nextInt()));
        return preparedStatement.executeQuery();
    }


}
