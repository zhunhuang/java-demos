package learn.synchronize;

import org.junit.Test;

/**
 * @author: zhun.huang
 * @create: 2018-03-07 下午8:20
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TestSynchronized {

    /**
     * 两个线程获取同一个类的不同静态同步方法, 其中一个线程会被阻塞
     * 静态方法锁住的是当前的类
     */
    @Test
    public void twoThreadAccessStatic() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectA.staticSyncMethodA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectA.staticSyncMethodB();
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A线程获取类静态同步方法, B线程获取该类的对象的同步方法, 线程不会被阻塞
     * 静态同步方法锁住的是当前的类对象,实例同步方法锁住的是当前的实例对象,不存在资源共享
     */
    @Test
    public void twoThreadAccessStaticAndInstance() {
        ObjectA objectA = new ObjectA();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectA.staticSyncMethodA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                objectA.syncMethodA();
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 两个线程获取同一个对象的不同同步方法, 其中一个线程会被阻塞
     * 实例方法锁住的是当前的对象
     */
    @Test
    public void twoThreadAccessInstance() {
        ObjectA objectA = new ObjectA();
        new Thread(new Runnable() {
            @Override
            public void run() {
                objectA.syncMethodA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                objectA.syncMethodB();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 两个线程获取不同实例的同步方法, 线程不会被阻塞
     * 实例方法锁住的是当前的对象
     */
    @Test
    public void twoThreadAccessInstance2() {
        ObjectA objectA = new ObjectA();
        ObjectA objectB = new ObjectA();
        new Thread(new Runnable() {
            @Override
            public void run() {
                objectA.syncMethodA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                objectB.syncMethodB();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ObjectA {
        public synchronized void syncMethodA() {
            try {
                System.out.println("enter syncMethodA.....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void syncMethodB() {
            try {
                System.out.println("enter syncMethodB.....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static synchronized void staticSyncMethodA() {
            try {
                System.out.println("enter static syncMethodA.....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static synchronized void staticSyncMethodB() {
            try {
                System.out.println("enter static syncMethodB.....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
