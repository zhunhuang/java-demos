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
public class ThreadQueueFullAlertRule extends AbstractAlertRuleMatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadQueueFullAlertRule.class);

    @Override
    public List<AlertItem> match(PoolResource poolResource) {
        List<AlertItem> alertItemList = new ArrayList<>();
        final ResourceStats poolTarget = poolResource.getResourceStats();
        if (poolTarget == null) {
            LOGGER.warn("skip ThreadQueueFullAlertRule match, cause poolTarget is null, poolResource:{}",
                    poolResource.getName());
            return alertItemList;
        }
        long queueMaxSize = poolTarget.getMaxQueueSize();
        if (queueMaxSize <= 3) {
            LOGGER.debug(
                    "skip ThreadQueueFullAlertRule match, cause queueMaxSize is less than 3:[{}], poolResource:{}",
                    queueMaxSize, poolResource.getName());
            return alertItemList;
        }
        long queueSize = poolTarget.getCurrentQueueSize();
        if (queueSize >= queueMaxSize) {
            AlertItem alertItem = buildAlertItem(queueSize, queueMaxSize, poolResource.getName(),
                    poolResource.getName());
            alertItemList.add(alertItem);
        }
        return alertItemList;
    }

    @Override
    public String ruleName() {
        return "线程队列已满";
    }
}
