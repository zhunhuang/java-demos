package simple;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: zhun.huang
 * @create: 2018-04-25 下午9:25
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public interface DBSource {
    /**
     * 获取连接
     * @return 返回一个连接
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * 关闭该连接
     * @param connection
     * @throws SQLException
     */
    void closeConnection(Connection connection) throws SQLException;
}
