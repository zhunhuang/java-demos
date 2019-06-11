package service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: zhun.huang
 * @create: 2018-03-31 下午3:50
 * @email: nolan.zhun@gmail.com
 * @description:
 * 有关tomcat日志的wiki:https://www.zhihu.com/question/40854079/answer/88572573
 */
@Service
public class LoginService {

    @Resource
    private DataBaseService dataBaseService;

    public boolean login(String name, String password) {
        ResultSet resultSet = dataBaseService.executeQuery("select * from admin_user where name='" + name + "' and password='" + password + "'");
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("password"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
