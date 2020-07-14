/**
 * description:
 *
 * @author zhunhuang, 2020/7/1
 */
public class TEst2 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            test();
        }
    }

    public static void test() {
        try {
            TEst2.class.getClassLoader().loadClass("TEst2");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HelloTest h = new HelloTest();
        h.hello();
    }

    public static class HelloTest {

        public void hello() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("你好");
        }

    }
}
