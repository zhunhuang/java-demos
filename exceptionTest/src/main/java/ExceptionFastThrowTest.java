/**
 * description:
 *
 * @author zhunhuang, 2021/1/21
 */
public class ExceptionFastThrowTest {

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            try {
                test1();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void test1(){
        test2();
    }

    private static void test2() {
        throw new NullPointerException();
    }
}
