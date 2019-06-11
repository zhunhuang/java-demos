import alerter.AlertTaskExecutor;
import alerter.DefaultResourceAlerter;
import alerter.ResourceAlerter;
import definition.PoolResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scanner.ResourceScanner;
import scanner.ScannerTaskExecutor;
import scanner.ThreadPoolExecutorScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class PoolMonitorBootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoolMonitorBootstrap.class);

    private Map<PoolResourceType, ResourceScanner> resourceScannerMap;

    private ResourceAlerter resourceAlerter;

    public PoolMonitorBootstrap() {
        defaultConfig();
    }

    private void defaultConfig() {
        resourceAlerter = new DefaultResourceAlerter();
        resourceScannerMap = new HashMap<>(32);
        resourceScannerMap.put(PoolResourceType.ThreadPoolExecutor, new ThreadPoolExecutorScanner());
    }

    public void start() {
        ScannerTaskExecutor.getSingleton(resourceScannerMap).start();
        AlertTaskExecutor.getSingleton(resourceAlerter).start();
        LOGGER.info("池资源监控组件启动成功");
    }

    public PoolMonitorBootstrap addCustomResourceScanner(PoolResourceType type, ResourceScanner resourceScanner) {
        this.resourceScannerMap.put(type, resourceScanner);
        return this;
    }

    public PoolMonitorBootstrap setCustomResourceAlerter(ResourceAlerter resourceAlerter) {
        this.resourceAlerter = resourceAlerter;
        return this;
    }

    public void shutdownGraceful() {
        try {
            ScannerTaskExecutor.getSingleton(resourceScannerMap).stopGraceful();
            AlertTaskExecutor.getSingleton(resourceAlerter).stopGraceful();
        } catch (Throwable e) {
            LOGGER.error("池资源监控组件竟然停止失败了", e);
        }
    }
}
