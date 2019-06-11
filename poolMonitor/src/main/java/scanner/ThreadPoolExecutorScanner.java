package scanner;

import definition.PoolResource;
import definition.ResourceStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class ThreadPoolExecutorScanner implements ResourceScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolExecutorScanner.class);

    @Override
    public void doScan(PoolResource poolResource) {
        if (poolResource.getTarget() == null
                || !(poolResource.getTarget() instanceof ThreadPoolExecutor)) {
            LOGGER.warn("不应该啊，扫描的目标对象居然不存在或者类型不符合");
            return;
        }
        ThreadPoolExecutor target = (ThreadPoolExecutor) poolResource.getTarget();
        try {
            poolResource.setStatsInProgress(true);
            ResourceStats resourceStats = new ResourceStats();
            resourceStats.setActiveCount(target.getActiveCount());
            resourceStats.setCorePoolSize(target.getCorePoolSize());
            resourceStats.setMaxPoolSize(target.getMaximumPoolSize());
            resourceStats.setCurrentPoolSize(target.getPoolSize());
            resourceStats.setCurrentQueueSize(target.getQueue() == null ? -1 : target.getQueue().size());
            resourceStats.setMaxQueueSize(target.getQueue() == null ? -1 : target.getQueue().size() + target.getQueue().remainingCapacity());
            resourceStats.setQueueType(target.getQueue() == null ? null : target.getQueue().getClass());
            resourceStats.setTaskStats(null);
            poolResource.setResourceStats(resourceStats);
        } finally {
            LOGGER.debug("finish scanner resource:[{}], info:{}",poolResource.getName(),poolResource.getResourceStats());
            poolResource.setStatsInProgress(false);
        }
    }
}
