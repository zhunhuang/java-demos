package alerter.rule;

import alerter.AlertItem;
import definition.PoolResource;

import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public interface AlertRuleMatcher {

    List<AlertItem> match(PoolResource poolResource);

    String ruleId();

    String ruleName();

}
