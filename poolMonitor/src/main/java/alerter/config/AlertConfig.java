package alerter.config;

import alerter.rule.*;
import definition.PoolResourceType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class AlertConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertConfig.class);

    private static Map<PoolResourceType, List<AlertRuleMatcher>> ruleConfigMap;

    private static Map<AlertFeature, String> alertFeatureConfigMap;

    static {
        defaultConfig();
    }

    private static void defaultConfig() {
        LOGGER.info("设置alert默认配置");
        ruleConfigMap = new HashMap<>(32);
        List<AlertRuleMatcher> ThreadPoolExecutorAlerts = new ArrayList<>();
        ThreadPoolExecutorAlerts.add(new ThreadActiveFullAlertRule());
        ThreadPoolExecutorAlerts.add(new ThreadActiveMaxRatioAlertRule());
        ThreadPoolExecutorAlerts.add(new ThreadQueueFullAlertRule());
        ThreadPoolExecutorAlerts.add(new ThreadQueueMaxRateAlertRule());

        ruleConfigMap.put(PoolResourceType.ThreadPoolExecutor, ThreadPoolExecutorAlerts);


        alertFeatureConfigMap = new HashMap<>(32);
        alertFeatureConfigMap.put(AlertFeature.THREAD_ACTIVE_MAX_RATE,"0.8");
        alertFeatureConfigMap.put(AlertFeature.QUEUED_MAX_RATE,"0.5");
    }

    public static List<AlertRuleMatcher> getRules(PoolResourceType poolResourceType) {
        return ruleConfigMap.get(poolResourceType);
    }

    public static boolean needAlert(PoolResourceType poolResourceType) {
        return true;
    }

    public static double getDoubleConfig(AlertFeature feature, double defaultValue) {
        String value = alertFeatureConfigMap.get(feature);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            LOGGER.error("获取配置竟然失败了，是不是没有配置[],或者类型错了", feature);
        }
        return defaultValue;
    }
}
