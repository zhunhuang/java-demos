/**
 * @author: zhun.huang
 * @create: 2018-05-10 下午4:21
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class RootCauseTest {

    public static void main(String[] args) {
        try {
            Test1();

        } catch (Throwable t) {
           t.printStackTrace();
        }
    }

    public static void Test1() throws IllegalArgumentException {
        try {

            Test2();

        } catch (Exception e) {
            final IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
            illegalArgumentException.initCause(e);
            throw illegalArgumentException;
        }
    }

    public static void Test2() throws RuntimeException {
        throw new ArrayIndexOutOfBoundsException("越界啦");
    }


}
