package reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * description:
 *  在抛出内存溢出之前，先把软引用给清理了。
 *
 * @author zhun.huang 2019-07-31
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

        SoftReference<Integer> a = new SoftReference<Integer>(10);
        WeakReference<Integer> b = new WeakReference<Integer>(10);

        System.out.println(a.get());
        add(Integer.valueOf(10),Integer.valueOf(10));
    }

    static void add(int a, int b) {
        System.out.println(a+b);
        return;
    }
}
