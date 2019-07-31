package reference;

import java.lang.ref.*;

/**
 * description:
 *  在抛出内存溢出之前，先把软引用给清理了。
 *
 * @author zhun.huang 2019-07-31
 */
public class SoftReferenceDemo {

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        // 可以用来做缓存
        SoftReference<Object> a = new SoftReference<Object>(new Object());

        // 可以用来做缓存。 ThreadLocal也有类似的用法
        WeakReference<Object> b = new WeakReference<Object>(new Object());

        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();

        Object observerObject = new Object();
        // 可以用来观察对象被回收操作
        PhantomReference<Object> c = new PhantomReference<Object>(observerObject, objectReferenceQueue);

        System.out.println(o);
        System.out.println(a.get());
        System.out.println(b.get());
        System.out.println(c.get());
        for (int i = 0; i < 100; i++) {
            Reference<?> remove = objectReferenceQueue.remove(1);
            if (remove != null) {
                System.out.println("observerObject 被回收了");
                break;
            }
            Thread.sleep(10);
        }

        observerObject = null;
        System.gc();

        for (int i = 0; i < 100; i++) {
            Reference<?> remove = objectReferenceQueue.remove(1);
            if (remove != null) {
                System.out.println("observerObject 被回收了, i=" + i);
                break;
            }
            Thread.sleep(10);
        }
        System.out.println("结束");
    }
}
