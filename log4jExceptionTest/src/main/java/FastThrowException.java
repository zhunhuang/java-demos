/**
 * description:
 *
 * @author zhunhuang, 2020/7/3
 */
public class FastThrowException {

    public static void main(String[] args) {

        for (int i = 0; i < 10000000; i++) {
            try {
                test4(0);
            } catch (Exception e) {
                System.out.println("异常次数：" + i);
                e.printStackTrace();
            }
        }


    }

    public static void test4(int i) {
        test3(i);
    }

    public static void test3(int i) {
        test2(i);
    }

    public static void test2(int i) {
        test1(i);
    }

    public static void test1(int i) {
        test0(i);
    }

    public static void test0(int i) {
        throw new RuntimeException();
//        System.out.println(10 / i);
    }
}
