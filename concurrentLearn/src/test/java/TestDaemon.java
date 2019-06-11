import java.util.concurrent.TimeUnit;

/**
 * @author: zhun.huang
 * @create: 2018-04-13 下午5:19
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TestDaemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "daemon");
        //当虚拟机不存在daemon==false的线程时，虚拟机将会自动退出
        //mian线程属于false
        //不对这个属性进行设置的线程也是false
        //thread.setDaemon(false);
//        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("run ........");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("end run......");
                } catch (Exception e) {
                } finally {
                    System.out.println("finally is runing。。。。");
                }
            }
        }
    }
}
