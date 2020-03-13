package demos.mariadb4j;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * description:
 *
 * @author zhunhuang, 2020/3/4
 */
public class HelloMariaDb4j {

    private static DB db;

    @BeforeClass
    public static void setUpMariaDb4j() throws ManagedProcessException {
        db = DB.newEmbeddedDB(3406);
        db.start();
        db.createDB("test");
    }

    @AfterClass
    public static void teardownMariaDb4j() throws ManagedProcessException {
        db.stop();
    }

    @Test
    public void test() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3406/test", "root", "");
        PreparedStatement preparedStatement = conn.prepareStatement("select 1");
        boolean execute = preparedStatement.execute();
        System.out.println(execute);
    }
}
