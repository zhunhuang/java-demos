package alerter.rule;

import alerter.AlertItem;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public abstract class AbstractAlertRuleMatcher implements AlertRuleMatcher {

    public AlertItem buildAlertItem(double actualValue, double threshold, String resourceName, String jobName) {
        AlertItem alertItem = new AlertItem();
        alertItem.setAlertRuleId(ruleId());
        alertItem.setAlertRuleName(ruleName());
        alertItem.setResourceName(resourceName);
        alertItem.setJobName(jobName);
        alertItem.setTriggerValue(actualValue);
        alertItem.setTriggerThreshold(threshold);
        return alertItem;
    }

    @Override
    public String ruleId() {
        return this.getClass().getSimpleName();
    }
}
