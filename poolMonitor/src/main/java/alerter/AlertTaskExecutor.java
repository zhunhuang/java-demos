package alerter;

import definition.PoolResource;
import definition.PoolResourcesHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * create: 2018-09-06
 *
 * @author zhun.huang
 */
public class AlertTaskExecutor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertTaskExecutor.class);
    private static AlertTaskExecutor alertTaskExecutor;

    private long initialDelay;
    private long delay;
    private ResourceAlerter resourceAlerter;


    private AlertTaskExecutor(long initialDelay, long delay, ResourceAlerter resourceAlerter) {
        this.initialDelay = initialDelay;
        this.delay = delay;
        this.resourceAlerter = resourceAlerter;
    }

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    /**
     * execute scheduled
     */
    public void start() {

        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory
                        .Builder()
                        .namingPattern("module_alert-schedule-pool-%d").
                        daemon(true).build());
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, initialDelay, delay, TimeUnit.SECONDS);
    }

    /**
     * execute once
     */
    @Override
    public void run() {
        try {
            LOGGER.info("scheduled PoolResource alerter start....");
            for (int i = 0; i < PoolResourcesHolder.getAllResourcesStats().size(); i++) {
                PoolResource poolResource = PoolResourcesHolder.getAllResourcesStats().get(i);
                if (resourceAlerter == null) {
                    LOGGER.debug("scp|alert|未找到[{}]类型的报警器", poolResource.getPoolResourceType());
                    continue;
                }
                resourceAlerter.onTarget(poolResource);
            }
        } catch (Exception e) {
            LOGGER.error("module_alert-schedule run error", e);
        } finally {
            LOGGER.info("scheduled PoolResource alerter finished....");
        }
    }

    public void stopGraceful() {
        if (scheduledThreadPoolExecutor == null) {
            return;
        }
        try {
            scheduledThreadPoolExecutor.shutdownNow();
            releaseRefer();
        } catch (Throwable e) {
            LOGGER.error("module_alert-schedule shutdown err, cause ", e);
        }
    }

    private void releaseRefer() {
        AlertTaskExecutor.alertTaskExecutor = (null);
    }

    public static AlertTaskExecutor getSingleton(ResourceAlerter resourceAlerter) {
        if (alertTaskExecutor == null) {
            synchronized (AlertTaskExecutor.class) {
                if (alertTaskExecutor == null) {
                    alertTaskExecutor = new AlertTaskExecutor(2, 6, resourceAlerter);
                }
            }
        }
        return alertTaskExecutor;
    }
}
