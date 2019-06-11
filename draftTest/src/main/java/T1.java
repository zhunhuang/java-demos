/**
 * @author: zhun.huang
 * @create: 2018-03-29 下午4:15
 * @email: nolan.zhun@gmail.com
 * @description: 原始类型 强制转换, 会出现精度问题, 不可预期问题
 */
public class T1 {
    static int i =0;


    public static void main(String[] args) {
        Integer a = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (a) {
                        System.out.println("县城A start" + i);
                        i++;
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        a.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (a) {
                        System.out.println("县城B start" + i);
                        i++;
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        a.notifyAll();
                    }
                }
            }
        }).start();
    }
}
