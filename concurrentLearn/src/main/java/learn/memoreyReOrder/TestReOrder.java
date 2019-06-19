package learn.memoreyReOrder;

/**
 * @author: zhun.huang
 * @create: 2018-03-28 下午3:04
 * @email: nolan.zhun@gmail.com
 * @description: 测试内存重排序, 运行一定时间后会停止, 输出i=0
 * 停止原因是: write()方法的两行代码被重新排序了
 */
public class TestReOrder {

    public static int i=1;
    public static class ReorderExample {
        int a = 0;
        boolean flag = false;

        public void writer () {
            a = 1;
            flag = true;
        }

        public void reader() {
            if (flag) {
                i = a*a;
//                if (i != 1) {
                    System.out.print("i=" + i);
//                }
            }
            System.out.println("-");
        }
    }

    public static void main(String[] args) {
        while (i == 1) {
            ReorderExample reorderExample = new ReorderExample();

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    reorderExample.writer();
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    reorderExample.reader();
                }
            });
            thread1.start();
            thread2.start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
