package cpu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/4
 */
public class TestCPUSwitch {

    public static void main(String[] args) throws InterruptedException {
        // mac 4核心->6核，换算CPU利用率时，需要*1.5
        // 0.05毫秒->1毫秒 20倍，任务数需要增大20倍
        final int task = 6;
        ExecutorService executorService = Executors.newFixedThreadPool(task);
        Thread.sleep(10000);
        System.out.println("开始测试");
        for (int i = 0; i < task; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000_000; i++) {
                        // 最小睡1ms,
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        Thread.sleep(1000000);
    }
}
