package learn.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * description:
 *
 * @author zhun.huang 2018-11-28
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        // LockSupport是通过控制变量_counter来对线程阻塞唤醒进行控制的
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("unpark");
                // unpark会将_counter凭证置为1；且在前值=0的时候进行线程唤醒。=1不进行线程唤醒。
                LockSupport.unpark(thread);
            }
        }).start();
        System.out.println("park");
        // park会将_counter凭证置为0.且在前值=0的时候进行线程阻塞。
        LockSupport.parkUntil(System.currentTimeMillis()+10000);
        System.out.println("unparked");

        // 所以综上所述， 如果先进行unpark， 再进行park，是不会阻塞线程的。因为park的时候已经给了凭证了。
        // 其实就是拿凭证的过程，一开始没有凭证，所以park获取凭证就会阻塞，
        // 如果进行过unpark，表明发放过凭证，但最多只有1张，所以不管是先发凭证，还是后发凭证，都能够保证在发凭证之后线程不再阻塞。
    }
}
