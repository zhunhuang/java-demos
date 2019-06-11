package learn.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * description:
 *
 * @author zhun.huang 2018-11-16
 */
public class Mutex {

    public static class sync extends AbstractQueuedSynchronizer {


        public boolean isHeldLock() {
            return getState() == 1;
        }

        public boolean tryAcquire() {
            if (compareAndSetState(0, 1)) {
                //标记正在拥有这个锁的线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
    }

    public static class MockSync {
        private static final Unsafe unsafe = getUnsafe();

        private volatile int state;

        private long stateOffSet;

        {
            try {
                stateOffSet = unsafe.objectFieldOffset(MockSync.class.getDeclaredField("state"));
            } catch (NoSuchFieldException e) {
                throw new Error();
            }
        }

        protected boolean compareAndSetState(int expected, int updateTo) {
            return unsafe.compareAndSwapInt(this, stateOffSet, expected, updateTo);
        }

        static Unsafe getUnsafe() {
            try {
                Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
                unsafe.setAccessible(true);
                return (Unsafe) unsafe.get(null);
            } catch (Exception e) {
                throw new Error();
            }
        }
    }

    public static void main(String[] args) {
        MockSync mockSync = new MockSync();
        boolean result = mockSync.compareAndSetState(0, 1);
        System.out.println(result);
    }
}
