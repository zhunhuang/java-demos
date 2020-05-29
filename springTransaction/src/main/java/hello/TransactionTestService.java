package hello;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
@Service
public class TransactionTestService {

    @Resource
    private DataSource dataSource;

    @Transactional(rollbackFor = Exception.class)
    public String save(String name, boolean throwError) throws SQLException {
        ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        // 需确保拿到的是同一个连接
        Connection connection = connectionHolder.getConnection();
        // 错误用法
        // Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        statement.execute("insert into `user` values ('" + name + "','123456')");
        if (throwError) {
            throw new RuntimeException("模拟异常，回滚事务");
        }
        statement.execute("insert into `user` values ('nolan2','222222')");
        return "ok";
    }

    public String selectByName(String name) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from `user` where name='" + name + "'");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("password"));
                if (resultSet.getString("password") != null) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }
}
