package platform.service;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: zhun.huang
 * @create: 2018-03-31 下午3:50
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class DataBaseService {

    @Resource
    private SimpleDriverDataSource dataSource;

    ResultSet executeQuery(String sql) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
