package bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/6
 */

public class TestBigDecimal {

    public static void main(String[] args) {
        // 一定不要使用BigDecimal的构造函数
        BigDecimal bigDecimal1 = new BigDecimal(0.1, MathContext.DECIMAL64);
        BigDecimal bigDecimal2 = new BigDecimal(3.0, MathContext.DECIMAL128);
        System.out.println(bigDecimal1.multiply(bigDecimal2));

        BigDecimal bigDecimal3 = BigDecimal.valueOf(0.1);
        BigDecimal bigDecimal4 = BigDecimal.valueOf(3.0);
        System.out.println(bigDecimal3.multiply(bigDecimal4));

        /**
         * 关于BigDecimal的精度, 默认是没有精度限制。
         * 可以通过MathContext设置精度。
         * 有两个属性：
         * 1. precision ：某个操作使用的数字个数；结果舍入到此精度 roundingMode
         * 2. roundingMode: 超过精度precision长度时，如何进行舍入。
         * 默认的有：MathContext.DECIMAL128
         */
        System.out.println(new BigDecimal(0.1, MathContext.DECIMAL64).compareTo(new BigDecimal(0.1, MathContext.DECIMAL32)));
        System.out.println(new BigDecimal(0.1, MathContext.DECIMAL64).compareTo(new BigDecimal(0.1, MathContext.DECIMAL128)));
        System.out.println(new BigDecimal(0.1, MathContext.DECIMAL128).compareTo(new BigDecimal(0.1, MathContext.UNLIMITED)));
    }
}
