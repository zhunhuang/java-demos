package alerter;

import alerter.config.AlertConfig;
import alerter.rule.AlertRuleMatcher;
import definition.PoolResource;
import definition.PoolResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class DefaultResourceAlerter implements ResourceAlerter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResourceAlerter.class);

    @Override
    public void onTarget(PoolResource target) {
        if (target == null) {
            return;
        }

        if (needAlert(target.getPoolResourceType())) {
            List<AlertItem> alertItems = checkTarget(target);

            sendAlert(alertItems);
        }
    }

    private void sendAlert(List<AlertItem> alertItems) {
        LOGGER.info("发送报警....");
        LOGGER.error("报警信息：{}",alertItems);
    }

    private List<AlertItem> checkTarget(PoolResource target) {
        List<AlertRuleMatcher> rules = AlertConfig.getRules(target.getPoolResourceType());
        if (rules == null) {
            LOGGER.warn("类型为[{}]的池资源竟然没有配置任何报警规则", target.getPoolResourceType());
            return new ArrayList<>();
        }
        List<AlertItem> totalAlerts = new ArrayList<>();
        for (AlertRuleMatcher rule : rules) {
            List<AlertItem> alertItems = rule.match(target);
            if (alertItems != null && alertItems.size() > 0) {
                totalAlerts.addAll(alertItems);
            }
        }
        return totalAlerts;
    }

    private boolean needAlert(PoolResourceType poolResourceType) {
        return AlertConfig.needAlert(poolResourceType);
    }

}
