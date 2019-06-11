package com.test.nolan.statemachine.pay.service;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.spi.context.AbstractFlowContextFactory;
import com.test.nolan.db.PayDataDB;
import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.nolan.statemachine.pay.bean.PayFlowTrait;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午9:59
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Component
public class PayFlowContextFactory extends AbstractFlowContextFactory<PayFlowContext,PayFlowTrait> {
    private static final Logger logger = LoggerFactory.getLogger(PayFlowContextFactory.class);

    @Resource
    private PayDataDB payDataDB;
    @Override
    protected PayFlowTrait findFlowId(String flowId) {
        if (StringUtils.isBlank(flowId)) {
            throw new IllegalArgumentException("flowId is null");
        }
        PayRecordBean payRecordBean = payDataDB.getData(flowId);
        if (payRecordBean == null) {
            logger.error("找不到存储订单, flowId: {}", flowId);
            throw new IllegalArgumentException("flowId is null");
        }
        return new PayFlowTrait(payRecordBean);
    }

    @Override
    protected PayFlowContext createContext(PayFlowTrait flow, ExecutorInfo executorInfo) {
        if (flow == null) {
            throw new IllegalArgumentException("flow is null");
        }
        if (StringUtils.isBlank(flow.flowId())) {
            throw new IllegalArgumentException("flowId is null");
        }
        return new PayFlowContext(flow.flowId(), executorInfo, flow);
    }
}
