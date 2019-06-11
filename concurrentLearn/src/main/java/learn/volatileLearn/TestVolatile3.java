package learn.volatileLearn;

/**
 * description:
 * create: 2018-08-23
 *
 * @author zhun.huang
 */
public class TestVolatile3 {

    public static volatile int a;

    public static void main(String[] args) {
        int b = 1;
        a++;
    }
}
