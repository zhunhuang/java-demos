package com.test.nolan.statemachine.order.service;

import com.test.statemachine.core.model.OperationLog;
import com.test.statemachine.spi.flow.FlowStoreManager;
import com.test.nolan.db.OrderDataDB;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import com.test.nolan.statemachine.order.bean.OrderFlowTrait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午9:36
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class OrderService implements FlowStoreManager<OrderFlowTrait> {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderDataDB orderDataDB;

    @Override
    public OrderFlowTrait updateFlow(OrderFlowTrait orderFlowTrait, OperationLog operationLog) {
        HotelOrderBean orderBean = orderDataDB.getData(orderFlowTrait.flowId());
        if (orderBean != null) {
            logger.info("更新订单信息, orderFlowTrait:{}", orderFlowTrait);
            orderDataDB.putData(orderFlowTrait.getHotelOrderBean());
            return orderFlowTrait;
        }
        logger.error("更新订单信息失败, 找不到存储的订单信息, orderFlowTrait : {}", orderFlowTrait);
        return orderFlowTrait;
    }

    @Override
    public OrderFlowTrait saveFlow(OrderFlowTrait orderFlowTrait, OperationLog operationLog) {
        logger.info("保存订单信息, orderBean:{}", orderFlowTrait.getHotelOrderBean());
        orderDataDB.putData(orderFlowTrait.getHotelOrderBean());
        return orderFlowTrait;
    }

    @Override
    public OrderFlowTrait queryFlow(String s) {
        System.out.println("查询订单信息, flowId:{}"+ s);
        return new OrderFlowTrait(orderDataDB.getData(s));
    }

    public OperationLog buildOperationLog(OrderFlowContext orderFlowContext){
        OperationLog operationLog = new OperationLog();
        operationLog.setContent(orderFlowContext.getLogComment());
        operationLog.setExecutorInfo(operationLog.getExecutorInfo());
        operationLog.setSecurityLevel(50);
        return operationLog;
    }
}
