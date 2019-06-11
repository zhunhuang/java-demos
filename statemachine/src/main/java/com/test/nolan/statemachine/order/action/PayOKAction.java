package com.test.nolan.statemachine.order.action;

import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.hotel.service.HotelMockService;
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
public class PayOKAction implements FlowAction<OrderFlowContext> {

    private static final Logger logger = LoggerFactory.getLogger(PayOKAction.class);

    @Resource
    private HotelMockService hotelMockService;

    @Override
    public FlowTrait execute(OrderFlowContext context) throws Exception {
        HotelOrderBean orderBean = context.getFlow().getHotelOrderBean();
        // 通知酒店确认房源
        hotelMockService.confirmReserve(orderBean.getOrderNo());
        return null;
    }

    @Override
    public String getLabel() {
        return "[支付成功]";
    }
}
