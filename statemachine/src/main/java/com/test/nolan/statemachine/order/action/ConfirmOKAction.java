package com.test.nolan.statemachine.order.action;

import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.client.service.UserMockService;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-16 下午12:12
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Component
public class ConfirmOKAction implements FlowAction<OrderFlowContext> {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmOKAction.class);

    @Resource
    private UserMockService userMockService;

    @Override
    public FlowTrait execute(OrderFlowContext context) throws Exception {
        HotelOrderBean orderBean = context.getFlow().getHotelOrderBean();
        // 通知用户房源已经确定
        userMockService.mockRecieveMessage("亲爱的用户您好, 您预定的酒店已经锁定房源, 酒店Id: "+orderBean.getHotelId() + ", 订单号: " + orderBean.getOrderNo());
        return null;
    }

    @Override
    public String getLabel() {
        return "[支付成功]";
    }
}
