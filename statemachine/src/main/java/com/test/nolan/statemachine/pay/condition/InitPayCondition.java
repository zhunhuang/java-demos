package com.test.nolan.statemachine.pay.condition;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.exception.FlowConditionCheckException;
import com.test.statemachine.spi.action.FlowCondition;
import com.test.nolan.db.OrderDataDB;
import com.test.nolan.statemachine.bank.service.BankMockService;
import com.test.nolan.statemachine.hotel.service.HotelMockService;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:13
 * @email: nolan.zhun@gmail.com
 * @description: 查看订单支付数据是否正确. 酒店是否有库存.
 */
@Component
public class InitPayCondition implements FlowCondition<PayFlowContext> {

    private static final Logger logger = LoggerFactory.getLogger(InitPayCondition.class);

    @Resource
    private BankMockService bankMockService;
    @Resource
    private HotelMockService hotelMockService;
    @Resource
    private OrderDataDB orderDataDB;

    @Override
    public void check(PayFlowContext context) throws FlowConditionCheckException {
        PayRecordBean payRecordBean = context.getFlow().getPayRecordBean();
        if (context.getFlow() == null) {
            context.setLogComment(context.getLogComment().concat("\n PayCondition check fail, flow is null"));
            throw new RuntimeException("flow is null");
        }
        if (context.getExecutorInfo().getExecutorRole() != ExecutorInfo.ExecutorRole.USER) {
            context.setLogComment(context.getLogComment().concat("\n PayCondition check fail, executorRole should be User"));
            throw new RuntimeException("executorRole should be User");
        }
        if (payRecordBean.getPayPrice() > 0
                && bankMockService.getUserAccount().getRemainNum() < payRecordBean.getPayPrice()) {
            context.setLogComment(context.getLogComment().concat("\n PayCondition check fail, 用户余额不足"));
            throw new RuntimeException("用户余额不足");
        }
        if (payRecordBean.getGuaranteePrice() > 0
                && bankMockService.getUserAccount().getRemainNum() < payRecordBean.getPayPrice()) {
            context.setLogComment(context.getLogComment().concat("\n PayCondition check fail, 用户余额不足"));
            throw new RuntimeException("用户余额不足");
        }
        HotelOrderBean orderBean = orderDataDB.getData(context.getFlowId());
        if (orderBean == null) {
            context.setLogComment(context.getLogComment().concat("\n PayCondition check fail, 找不到订单"));
            logger.error("找不到对应的订单信息, orderNoL: {}", context.getFlowId());
            throw new RuntimeException("找不到订单信息");
        }
        if (orderBean.getPayPrice() != payRecordBean.getPayPrice()) {
            logger.error("支付价格错误, 支付价格: {}, 实际价格: {}", payRecordBean.getPayPrice(), orderBean.getPayPrice());
            throw new RuntimeException("支付价格错误");
        }
    }

    @Override
    public String getLabel() {
        return "[支付参数校验]";
    }
}
