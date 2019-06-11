package com.test.nolan.statemachine.order.service;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.spi.context.AbstractFlowContextFactory;
import com.test.nolan.db.OrderDataDB;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.bean.OrderFlowTrait;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午7:55
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class OrderFlowContextFactory extends AbstractFlowContextFactory<OrderFlowContext, OrderFlowTrait>{

    @Resource
    private OrderDataDB orderDataDB;

    @Override
    protected OrderFlowTrait findFlowId(String s) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("flowId is empty");
        }
        HotelOrderBean orderBean = orderDataDB.getData(s);
        if (orderBean == null) {
            throw new IllegalArgumentException("flowId is not found");
        }
        return new OrderFlowTrait(orderBean);
    }

    @Override
    protected OrderFlowContext createContext(OrderFlowTrait orderFlowTrait, ExecutorInfo executorInfo) {
        if (orderFlowTrait == null) {
            throw new IllegalArgumentException("OrderFlowTrait is null");
        }
        if (StringUtils.isBlank(orderFlowTrait.flowId())) {
            throw new IllegalArgumentException("flowId is null");
        }
        return new OrderFlowContext(orderFlowTrait.flowId(), executorInfo, orderFlowTrait);
    }
}
