package Double;

import java.math.BigDecimal;

/**
 * description:
 *
 * @author zhunhuang, 2020/2/17
 */
public class TestDouble {

    public static void main(String[] args) {
        System.out.println(15.3d);
        System.out.println(14.7d + 0.6d);
        System.out.println((14.7d + 0.6d) == 15.3d);

        System.out.println(BigDecimal.valueOf(0.6));
        System.out.println(new BigDecimal(0.6));
    }
}
