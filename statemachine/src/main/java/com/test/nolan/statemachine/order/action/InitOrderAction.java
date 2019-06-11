package com.test.nolan.statemachine.order.action;

import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.hotel.service.HotelMockService;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import com.test.nolan.statemachine.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午9:56
 * @email: nolan.zhun@gmail.com
 * @description: 订单初始化动作
 */
@Component
public class InitOrderAction implements FlowAction<OrderFlowContext> {
    private static final Logger logger = LoggerFactory.getLogger(InitOrderAction.class);

    @Resource
    private OrderService orderService;
    @Resource
    private HotelMockService hotelMockService;

    @Override
    public FlowTrait execute(OrderFlowContext context) throws Exception {
        //订单流, 模拟http通知商家预定酒店
        String hotelId = hotelMockService.reserveHotel(context.getFlow().flowId());
        if ("".equals(hotelId)) {
            throw new RuntimeException("酒店预定失败");
        }
        context.getFlow().getHotelOrderBean().setHotelId(hotelId);
        orderService.saveFlow(context.getFlow(), orderService.buildOperationLog(context));
        return context.getFlow();
    }

    @Override
    public String getLabel() {
        return "初始化订单";
    }
}
