package com.test.nolan.statemachine.order.service;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.FlowStateMachineManager;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.bean.OrderFlowTrait;
import com.test.nolan.statemachine.order.enums.OrderStatus;
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
public class OrderPort {

    private static final Logger logger = LoggerFactory.getLogger(OrderPort.class);

    @Resource(name = "orderRefundStateMachine")
    private FlowStateMachineManager flowStateMachineManager;

    public boolean save(HotelOrderBean orderBean) {
        OrderFlowTrait orderFlowTrait = new OrderFlowTrait(orderBean);
        ExecutorInfo executorInfo = new ExecutorInfo();
        executorInfo.setExecutor(ExecutorInfo.ExecutorRole.USER.getLabel());
        executorInfo.setExecutorRole(ExecutorInfo.ExecutorRole.USER);
        try {
            flowStateMachineManager.generateFlow(orderFlowTrait,executorInfo);
            return true;
        } catch (Exception e) {
            logger.error("订单入库异常, orderBean: {}", orderBean, e);
            return false;
        } finally {
            logger.info("{} 初始化订单完成....", orderBean.getOrderNo());
        }
    }

    public void changeOrderStatus(String orderNo, OrderStatus toStatus, ExecutorInfo.ExecutorRole role) {
        ExecutorInfo executorInfo = new ExecutorInfo();
        executorInfo.setExecutor(role.getLabel());
        executorInfo.setExecutorRole(role);

        try {
            flowStateMachineManager.changeState(orderNo, toStatus.name(),0,executorInfo);
        } catch (Exception e) {
            logger.error("修改订单状态异常: orderNo: {}, toStatus{}", orderNo, toStatus, e);
        }
    }

}
