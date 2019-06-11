package com.test;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class AsyncWorker {
    private static final Logger log = LoggerFactory.getLogger(AsyncWorker.class);

    //配置参数
    private static final int corePoolSize = 2;
    private static final int maxPoolSize = 10;
    private static final int maxQueueSize = 1000;
    //单位采用秒
    private static final int keepAliveTime = 60;
    private static final ThreadPoolExecutor worker = new ThreadPoolExecutor(
            corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(maxQueueSize),
            new BasicThreadFactory.Builder().namingPattern("AsyncWorker-pool-%d").daemon(true).build(),
            new ThreadPoolExecutor.AbortPolicy());

    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("PoolMonitor-schedule-pool-%d").daemon(true).build());

    static {
        executorService.scheduleAtFixedRate(() -> {
        }, 2, 20, TimeUnit.MILLISECONDS);
    }

    private static void recordRejected() {
        log.warn("AsyncWorker recordRejected !!!!");
    }

    public static void execute(final Runnable runnable) {
        worker.execute(runnable);
    }

    public static void shutdown() {
        if (worker != null) {
            worker.shutdownNow();
        }
    }

    //监控Executor
    public static long getCompletedTaskCount() {
        return worker.getCompletedTaskCount();
    }

    public static int getPoolSize() {
        return worker.getPoolSize();
    }

    public static int getActiveCount() {
        return worker.getActiveCount();
    }

    public static int getMaximumPoolSize() {
        return worker.getMaximumPoolSize();
    }

    public static void setMaximumPoolSize(int size) {
        worker.setMaximumPoolSize(size);
    }

    public static int getCorePoolSize() {
        return worker.getCorePoolSize();
    }

    public static void setCorePoolSize(int size) {
        worker.setCorePoolSize(size);
    }

    public static int getQueueSize() {
        return worker.getQueue().size();
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (i < 100) {
            i++;
            final int j = i;
            Thread.sleep(10);
            AsyncWorker.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "   i=" + j);
                }
            });
        }
        Thread.sleep(5000);
    }
}
