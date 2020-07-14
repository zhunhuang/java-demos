package classLoader;

/**
 * description:
 *
 * @author zhunhuang, 2020/7/3
 */
public class ClassLoaderPath {

    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("============分割线");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("============分割线");
        System.out.println(System.getProperty("java.class.path"));
    }
}
