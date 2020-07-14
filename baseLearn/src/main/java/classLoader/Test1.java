package classLoader;

/**
 * description:
 *
 * @author zhunhuang, 2020/7/3
 */
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("java.lang.Class");
        System.out.println(aClass.getSimpleName());

        aClass = Class.forName("sun.reflect.DelegatingMethodAccessorImpl");
        System.out.println(aClass.getSimpleName());
    }
}
