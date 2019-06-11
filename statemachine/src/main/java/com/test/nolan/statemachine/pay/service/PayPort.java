package com.test.nolan.statemachine.pay.service;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.FlowStateMachineManager;
import com.test.nolan.statemachine.pay.bean.PayFlowTrait;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import com.test.nolan.statemachine.pay.condition.InitPayCondition;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午10:39
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class PayPort {
    private static final Logger logger = LoggerFactory.getLogger(PayPort.class);

    @Resource(name = "payCoreStateMachine")
    private FlowStateMachineManager payCoreStateMachine;
    @Resource
    private InitPayCondition initPayCondition;

    public boolean save(PayRecordBean payRecordBean) {
        PayFlowTrait payFlowTrait = new PayFlowTrait(payRecordBean);
        ExecutorInfo executorInfo = new ExecutorInfo();
        executorInfo.setExecutor(ExecutorInfo.ExecutorRole.USER.getLabel());
        executorInfo.setExecutorRole(ExecutorInfo.ExecutorRole.USER);
        try {
            payCoreStateMachine.generateFlow(payFlowTrait,executorInfo);
            logger.info("{} 支付流水初始化完成....", payRecordBean.getOrderNo());
            return true;
        } catch (Exception e) {
            logger.error("支付流水入库异常, orderNo: {}",payRecordBean.getOrderNo(), e);
            return false;
        }
    }

    public void changeOrderStatus(String orderNo, PayStatus toStatus, ExecutorInfo.ExecutorRole role) {
        ExecutorInfo executorInfo = new ExecutorInfo();
        executorInfo.setExecutor(role.getLabel());
        executorInfo.setExecutorRole(role);

        try {
            payCoreStateMachine.changeState(orderNo, toStatus.name(),0,executorInfo);
        } catch (Exception e) {
            logger.error("修改支付流水状态至[{}]异常: ", toStatus, e);
        }
    }

}
