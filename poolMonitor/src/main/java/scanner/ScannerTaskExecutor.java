package scanner;

import definition.PoolResource;
import definition.PoolResourceType;
import definition.PoolResourcesHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * create: 2018-09-06
 *
 * @author zhun.huang
 */
public class ScannerTaskExecutor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScannerTaskExecutor.class);
    private static ScannerTaskExecutor alertTaskExecutor;

    private long initialDelay;
    private long delay;
    private Map<PoolResourceType, ResourceScanner> resourceScannerMap;

    private ScannerTaskExecutor(long initialDelay, long delay, Map<PoolResourceType, ResourceScanner> resourceScannerMap) {
        this.initialDelay = initialDelay;
        this.delay = delay;
        this.resourceScannerMap = resourceScannerMap;
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
            LOGGER.info("scheduled PoolResource scanner start....");
            for (int i = 0; i < PoolResourcesHolder.getAllResourcesStats().size(); i++) {
                PoolResource poolResource = PoolResourcesHolder.getAllResourcesStats().get(i);
                ResourceScanner resourceScanner = resourceScannerMap.get(poolResource.getPoolResourceType());
                if (resourceScanner == null) {
                    LOGGER.debug("scp|alert|未找到[{}]类型的扫描器", poolResource.getPoolResourceType());
                    continue;
                }
                resourceScanner.doScan(poolResource);
            }
        } catch (Exception e) {
            LOGGER.error("module_scanner-schedule run error", e);
        } finally {
            LOGGER.info("scheduled PoolResource scanner finished....");
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
            LOGGER.error("module_scanner-schedule shutdown err, cause ", e);
        }
    }

    private void releaseRefer() {
        alertTaskExecutor = (null);
        resourceScannerMap = null;
    }

    public static ScannerTaskExecutor getSingleton(Map<PoolResourceType, ResourceScanner> resourceScannerMap) {
        if (alertTaskExecutor == null) {
            synchronized (ScannerTaskExecutor.class) {
                if (alertTaskExecutor == null) {
                    alertTaskExecutor = new ScannerTaskExecutor(2, 6, resourceScannerMap);
                }
            }
        }
        return alertTaskExecutor;
    }
}
