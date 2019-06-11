package simple;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: zhun.huang
 * @create: 2018-04-25 下午9:26
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class BasicDBSource implements DBSource {

    private Properties properties;

    private String url;
    private String user;
    private String password;
    private int max;
    private List<Connection> connections;

    public BasicDBSource()throws IOException,ClassNotFoundException{
        this("jdbc.properties");//调用带参的构造函数
    }

    private BasicDBSource(String configFile)throws IOException,ClassNotFoundException{
        //获取属性文件，因为我将url,user,password,max,driver的属性值存放在一个myjdbc.properties的属性文件里
        //这样设计的目的也是为了提高代码的低偶合度，为重构做些小小的贡献
        properties=new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile));
        url=properties.getProperty("url");
        user=properties.getProperty("username");
        password=properties.getProperty("password");
        max=Integer.parseInt(properties.getProperty("poolSize"));
        Class.forName(properties.getProperty("driverClassName"));
        connections= new ArrayList<>();
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (connections.size()==0) {
            return DriverManager.getConnection(url,user,password);
        } else {
            // 从池里拿出一个连接
            int last = connections.size()-1;
            return connections.remove(last);
        }
    }

    @Override
    public synchronized void closeConnection(Connection connection) throws SQLException {
        if (connections.size()>max) {
            connection.close();
        } else {
            connections.add(connection);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
