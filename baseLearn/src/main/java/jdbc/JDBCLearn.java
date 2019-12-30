package jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/12
 */
public class JDBCLearn {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");

        for (int i = 213123; i < 1000000; i++) {
            String business_party_id = "12016";
            String outNo = "out_" + String.valueOf(i);
            int fee_detail_no = i+1;
            String trade_no = "trade_" + i;
            BigDecimal feeAmount = BigDecimal.valueOf(100);

            String sql = "INSERT INTO test.fee_detail (fee_detail_no, business_party_id, fee_scene, out_no, product_id, fee_subject_type, fee_subject_id, fee_amount, currency, fee_cycle, fee_mode, fee_type, fee_direction, fee_rule_detail, fee_rate, trade_no, refund_no, trade_amount, trade_type, ext, status, create_time, modify_time) " +
                    "VALUES (?, ?, 1, ?, 123, 1, '34', ?, 1, 1, 2, 1, 1, '213123fsfsfsffdgdfsgdsafsfsdaf', 0.038000, ?, '123131', 110, 1, '', 0, '2019-11-12 22:45:39', '2019-12-12 22:45:32');";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fee_detail_no);
            preparedStatement.setString(2, business_party_id);
            preparedStatement.setString(3, outNo);
            preparedStatement.setBigDecimal(4, feeAmount);
            preparedStatement.setString(5, trade_no);
            preparedStatement.execute();
            System.out.println(i);
        }

    }
}
