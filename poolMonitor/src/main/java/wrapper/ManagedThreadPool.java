package wrapper;

import definition.PoolResourceType;
import register.ManualRegister;

import java.util.concurrent.*;

/**
 * description:
 * 使用该线程池实例化，即可实现池内资源的监控
 * @author zhun.huang 2019-03-26
 */
public class ManagedThreadPool extends ThreadPoolExecutor {

    // 任务执行时长
    private static ThreadLocal<Long> time;
    // 任务名称
    private static ThreadLocal<String> tname;

    public ManagedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        ManualRegister.register(PoolResourceType.ManagedThreadPool,this);
    }

    public ManagedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        ManualRegister.register(PoolResourceType.ManagedThreadPool,this);
    }

    public ManagedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        ManualRegister.register(PoolResourceType.ManagedThreadPool,this);
    }

    public ManagedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        ManualRegister.register(PoolResourceType.ManagedThreadPool,this);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        try {
            tname.set(r.getClass().getName());
            time.set(System.currentTimeMillis());
        } catch (Exception e) {

        }
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            System.out.println("方法执行时长："+ (System.currentTimeMillis() - time.get()));
            time.remove();
            tname.remove();
        } catch (Exception e) {

        }
        super.afterExecute(r, t);
    }
}
