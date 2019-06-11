package simple;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * description:
 * create: 2018-07-22
 *
 * @author zhun.huang
 */
public class DruidDBSource {
    public static DataSource dataSource = null;

    static {
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.err.println("初始化连接池失败: " + e);
        }
    }
}
