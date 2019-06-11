package alerter;

import definition.PoolResource;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public interface ResourceAlerter {

    /**
     * 扫描资源，检查报警指标，并发送报警
     *
     * @param target 被扫描的资源对象
     */
    void onTarget(PoolResource target);
}
