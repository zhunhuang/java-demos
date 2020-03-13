package bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * description:
 *
 * @author zhunhuang, 2020/3/11
 */
public class TestBigDecimalToString {

    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(1.233333333333333);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());

        bigDecimal = BigDecimal.valueOf(123123213131312313.31313123213123123);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());

        bigDecimal = BigDecimal.valueOf(1.2333333333333333);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());


        bigDecimal = BigDecimal.valueOf(19.233333333333333);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());


        bigDecimal = new BigDecimal(String.valueOf(1.2333333333333333333333), MathContext.DECIMAL128);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());


        bigDecimal = new BigDecimal(String.valueOf(1.2333333333333333333333), MathContext.DECIMAL64);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());


        bigDecimal = new BigDecimal(String.valueOf(1.2333333333333333333333), MathContext.DECIMAL32);
        System.out.println(bigDecimal.toPlainString());
        System.out.println(bigDecimal.toString());

    }
}
