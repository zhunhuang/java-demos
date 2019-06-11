package alerter.rule;

import alerter.AlertItem;
import definition.PoolResource;
import definition.ResourceStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class ThreadActiveFullAlertRule extends AbstractAlertRuleMatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadActiveFullAlertRule.class);

    @Override
    public List<AlertItem> match(PoolResource poolResource) {
        List<AlertItem> alertItemList = new ArrayList<>();
        final ResourceStats poolTarget = poolResource.getResourceStats();
        if (poolTarget == null) {
            LOGGER.warn("skip ThreadActiveFullAlertRule match, cause poolTarget is null, poolResource:{}",
                    poolResource.getName());
            return alertItemList;
        }
        long maxSize = poolTarget.getMaxPoolSize();
        if (maxSize <= 0) {
            LOGGER.info(
                    "skip ThreadActiveFullAlertRule match, cause maxSize is less than zero:[{}], poolResource:{}",
                    maxSize, poolResource.getName());
            return alertItemList;
        }
        long activeSize = poolTarget.getActiveCount();
        //忽略掉2个大小的线程池
        if (maxSize < 3) {
            LOGGER.debug("skip too small pool");
            return alertItemList;
        }
        if (activeSize >= maxSize) {
            AlertItem alertItem = buildAlertItem(activeSize, maxSize, poolResource.getName(),
                    poolResource.getName());
            alertItemList.add(alertItem);
        }
        return alertItemList;
    }

    @Override
    public String ruleName() {
        return "活跃线程数已满";
    }
}
