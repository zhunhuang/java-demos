package com.test.nolan.statemachine.pay.service;

import com.test.statemachine.core.model.OperationLog;
import com.test.statemachine.spi.flow.FlowStoreManager;
import com.test.nolan.db.LogDataDB;
import com.test.nolan.db.PayDataDB;
import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.nolan.statemachine.pay.bean.PayFlowTrait;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
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
public class OrderPayService implements FlowStoreManager<PayFlowTrait> {

    private static final Logger logger = LoggerFactory.getLogger(OrderPayService.class);
    @Resource
    private PayDataDB payDataDB;
    @Resource
    private LogDataDB logDataDB;

    @Override
    public PayFlowTrait updateFlow(PayFlowTrait payFlowTrait, OperationLog operationLog) {
        PayRecordBean data = payDataDB.getData(payFlowTrait.flowId());
        if (data != null) {
            logger.info("更新支付信息, payFlowTrait:{}", payFlowTrait);
            payDataDB.putData(payFlowTrait.getPayRecordBean());
            operationLog.setContent(operationLog.getContent() + ("更新支付信息, flowId: " + payFlowTrait.flowId()));
            logDataDB.putLog(operationLog);
        } else {
            logger.error("更新支付信息失败,找不到支付信息, flowId: {}", payFlowTrait.flowId());
            operationLog.setContent(operationLog.getContent()  + ("更新支付信息失败,找不到支付信息, flowId: " + payFlowTrait.flowId()));
            logDataDB.putLog(operationLog);
        }
        return payFlowTrait;
    }

    @Override
    public PayFlowTrait saveFlow(PayFlowTrait payFlowTrait, OperationLog operationLog) {
        logger.info("保存支付信息, flowId: "+ payFlowTrait.flowId());
        payDataDB.putData(payFlowTrait.getPayRecordBean());
        operationLog.setContent(operationLog.getContent() + ("保存支付信息, flowId: " + payFlowTrait.flowId()));
        logDataDB.putLog(operationLog);
        return payFlowTrait;
    }

    @Override
    public PayFlowTrait queryFlow(String s) {
        logger.info("查询支付信息, flowId:{}", s);
        return new PayFlowTrait(payDataDB.getData(s));
    }

    public OperationLog buildOperationLog(PayFlowContext payFlowContext){
        OperationLog operationLog = new OperationLog();
        operationLog.setContent(payFlowContext.getLogComment());
        operationLog.setExecutorInfo(payFlowContext.getExecutorInfo());
        operationLog.setSecurityLevel(100);
        return operationLog;
    }
}
