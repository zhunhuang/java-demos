package druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * description:
 * create: 2018-07-22
 *
 * @author zhun.huang
 */
public class Main {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user limit 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            Integer age = resultSet.getInt(3);
            System.out.println(id + ", " + name + ", " + age);
        }
    }
}
