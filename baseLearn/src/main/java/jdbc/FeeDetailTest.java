package jdbc;

import java.sql.*;

/**
 * description:
 *
 * @author zhunhuang, 2019/12/12
 */
public class FeeDetailTest {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "123456");
//        Connection connection = null;

        int subjectId = 0;
        int subjectCount = 200;
        int numEachSubject = 20000;
        for (subjectId = 101; subjectId < subjectCount; subjectId++) {
            for (int num = 10000; num < numEachSubject; num++) {
                String outNo = "out_" + subjectId + "_" + num;
                int fee_detail_no = subjectId * numEachSubject + num;
                int status = 5;
                if (num % 100 == 0) {
                    status = 0;
                }
//                Timestamp time = new Timestamp(1576033647000L + num * 1000);
                Timestamp time = new Timestamp(1576042881000L + num * 1000);

                String sql = "insert into test.bill_storage";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, fee_detail_no);
                preparedStatement.setString(2, outNo);
                preparedStatement.setString(3, "fd" + subjectId);
                preparedStatement.setString(4, "pc" + subjectId);
                preparedStatement.setString(5, "sj" + subjectId);
                preparedStatement.setInt(6, status);
                preparedStatement.setTimestamp(7, time);
                preparedStatement.execute();
                System.out.println(outNo);
            }
        }
    }

}
