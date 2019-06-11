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
public class ThreadActiveMaxRatioAlertRule extends AbstractAlertRuleMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadActiveMaxRatioAlertRule.class);

    @Override
    public List<AlertItem> match(PoolResource poolResource) {
        List<AlertItem> alertItemList = new ArrayList<>();
        final ResourceStats resourceStats = poolResource.getResourceStats();
        if (resourceStats == null) {
            LOGGER.debug("skip ThreadActiveMaxRatioAlertRule match, cause poolTarget is null, poolResource:{}",
                    poolResource.getName());
            return alertItemList;
        }
        // 默认0.8
        double maxRateLimit = AlertConfig.getDoubleConfig(AlertFeature.THREAD_ACTIVE_MAX_RATE,0.8);
        long maxSize = resourceStats.getMaxPoolSize();
        long activeSize = resourceStats.getActiveCount();
        if (maxSize <= 0) {
            LOGGER.debug(
                    "skip ThreadActiveMaxRatioAlertRule match, cause maxSize is less than zero:[{}], poolResource:{}",
                    maxSize, poolResource.getName());
            return alertItemList;
        }

        long activeSizeLimit = (long) ((double) maxSize * maxRateLimit);
        /*
         * qmq 线程池 maxSize,core Size 特别小，这种预警貌似没有意义,所以我们skip掉activeLimit 小于3 的
         * **/
        if (activeSizeLimit < 3) {
            LOGGER.debug("scp|alert|active limit is to low |{}", activeSizeLimit);
            return alertItemList;
        }

        // 活动线程比预计值要高
        if (activeSize > activeSizeLimit) {
            AlertItem alertItem = buildAlertItem(activeSize, activeSizeLimit, poolResource.getName(),
                    poolResource.getName());
            alertItemList.add(alertItem);
        }
        return alertItemList;
    }

    @Override
    public String ruleName() {
        return "活跃线程数过高";
    }
}
