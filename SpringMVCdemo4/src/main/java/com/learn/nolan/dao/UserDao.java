package com.learn.nolan.dao;

import com.learn.nolan.model.UserDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description:
 *
 * @author zhun.huang 2018-12-17
 */
@Repository
public class UserDao {

    @Resource
    private DataSource dataSource;

    public UserDO queryUser() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from user");
            UserDO userDO = new UserDO();
            while (resultSet.next()) {
                userDO.setId(resultSet.getLong("id"));
                userDO.setName(resultSet.getString("name"));
                userDO.setPassword(resultSet.getString("password"));
            }
            return userDO;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
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
}
