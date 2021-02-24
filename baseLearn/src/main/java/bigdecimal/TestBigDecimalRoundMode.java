package bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * description:
 *
 * @author zhunhuang, 2020/7/28
 */
public class TestBigDecimalRoundMode {

    public static void main(String[] args) {
        sout(1.5);
        sout(2.5);
        sout(2.0);
        sout(-1.5);
        sout(2.499);
        sout(-2.501);
        sout(1.500001);
        sout(-1.500001);

        System.out.println(0.123456 + ",FLOOR:" + BigDecimal.valueOf(0.123456).setScale(4, RoundingMode.FLOOR));
        System.out.println(-0.123456 + ",FLOOR:" + BigDecimal.valueOf(-0.123456).setScale(4, RoundingMode.FLOOR));
        System.out.println(0.12341 + ",FLOOR:" + BigDecimal.valueOf(0.12341).setScale(4, RoundingMode.FLOOR));
        System.out.println(-0.12341 + ",FLOOR:" + BigDecimal.valueOf(-0.12341).setScale(4, RoundingMode.FLOOR));
    }

    private static void sout(double val) {
        System.out.println(val + ",DOWN:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.DOWN));
        System.out.println(val + ",UP:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.UP));
        System.out.println(val + ",CEILING:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.CEILING));
        System.out.println(val + ",FLOOR:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.FLOOR));

        System.out.println(val + ",HALF_UP:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.HALF_UP));
        System.out.println(val + ",HALF_DOWN:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.HALF_DOWN));
        System.out.println(val + ",HALF_EVEN:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.HALF_EVEN));
        try {
            System.out.println(val + ",UNNECESSARY:" + BigDecimal.valueOf(val).setScale(0, RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println(val + ",UNNECESSARY: 不满足要求，需要四舍五入");
        }
    }
}
