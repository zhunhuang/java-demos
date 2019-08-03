package overLoad;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class OverLoadTest {

    public static class Father {

        private void test1(int a) {
            System.out.println("private father test1");
        }

        public void test2(int a) {
            System.out.println("public father test2");
        }

        public void test3(long a) {
            System.out.println("public father test3");
        }

        public void test4() {
            test1(1);
        }
    }

    public static class Sun extends Father {

        private void test1(int a) {
            System.out.println("private sun test1");
        }

        public void test2(int a) {
            System.out.println("public sun test2");
        }

        public void test3(String a) {
            System.out.println("public sun test3");
        }
    }

    public static void main(String[] args) {
        Father father = new Sun();
        // 静态绑定，编译器绑定，因为能确定了
        ((Sun)father).test1(1);
        // 静态绑定
        father.test1(1);
        // 重写， 动态绑定
        father.test2(1);
        // 重载，无关动态还是静态绑定
        father.test3(1);
        ((Sun)father).test3("a");
        // 被隐藏了。 结论， 千万别重写/覆盖 private方法。
        father.test4();
    }
}
