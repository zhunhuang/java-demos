package alerter.rule;

import alerter.AlertItem;
import alerter.config.AlertConfig;
import alerter.config.AlertFeature;
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
public class ThreadQueueMaxRateAlertRule extends AbstractAlertRuleMatcher{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadQueueMaxRateAlertRule.class);

    @Override
    public List<AlertItem> match(PoolResource poolResource) {
        List<AlertItem> alertItemList = new ArrayList<>();
        final ResourceStats poolTarget = poolResource.getResourceStats();
        if (poolTarget == null) {
            LOGGER.warn("skip ThreadQueueMaxRateAlertRule match, cause poolTarget is null, poolResource:{}",
                    poolResource.getName());
            return alertItemList;
        }
        double maxRateLimit = AlertConfig.getDoubleConfig(AlertFeature.QUEUED_MAX_RATE,0.8);
        long queueMaxSize = poolTarget.getMaxQueueSize();
        if (queueMaxSize <= 3) {
            LOGGER.debug(
                    "skip ThreadQueueMaxRateAlertRule match, cause queueMaxSize is less than 3:[{}], poolResource:{}",
                    queueMaxSize, poolResource.getName());
            return alertItemList;
        }
        long queueSize = poolTarget.getCurrentQueueSize();
        double queueRate = ((double) queueSize) / queueMaxSize;
        if (queueRate >= maxRateLimit) {
            AlertItem alertItem = buildAlertItem(queueRate, maxRateLimit, poolResource.getName(),
                    poolResource.getName());
            alertItemList.add(alertItem);
        }
        return alertItemList;
    }

    @Override
    public String ruleName() {
        return "线程队列比例过高";
    }
}
