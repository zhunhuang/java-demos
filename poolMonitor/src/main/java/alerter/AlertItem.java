package alerter;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class AlertItem {

    private String alertRuleId;

    private String alertRuleName;

    private double triggerValue;

    private double triggerThreshold;

    private String resourceName;

    private String jobName;

    private String alertDesc;

    public String getAlertRuleId() {
        return alertRuleId;
    }

    public void setAlertRuleId(String alertRuleId) {
        this.alertRuleId = alertRuleId;
    }

    public String getAlertRuleName() {
        return alertRuleName;
    }

    public void setAlertRuleName(String alertRuleName) {
        this.alertRuleName = alertRuleName;
    }

    public double getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(double triggerValue) {
        this.triggerValue = triggerValue;
    }

    public double getTriggerThreshold() {
        return triggerThreshold;
    }

    public void setTriggerThreshold(double triggerThreshold) {
        this.triggerThreshold = triggerThreshold;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAlertDesc() {
        return alertDesc;
    }

    public void setAlertDesc(String alertDesc) {
        this.alertDesc = alertDesc;
    }

    @Override
    public String toString() {
        return "{\"AlertItem\":{"
                + "\"alertRuleId\":\"" + alertRuleId + "\""
                + ", \"alertRuleName\":\"" + alertRuleName + "\""
                + ", \"triggerValue\":\"" + triggerValue + "\""
                + ", \"triggerThreshold\":\"" + triggerThreshold + "\""
                + ", \"resourceName\":\"" + resourceName + "\""
                + ", \"jobName\":\"" + jobName + "\""
                + ", \"alertDesc\":\"" + alertDesc + "\""
                + "}}";
    }
}
