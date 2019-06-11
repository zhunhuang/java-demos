package com.test.nolan.statemachine.pay.bean;

import com.test.statemachine.api.ActionRecorderStoreData;
import com.test.statemachine.api.FlowTrait;
import com.test.nolan.statemachine.pay.enums.PayStatus;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午8:38
 * @email: nolan.zhun@gmail.com
 * @description: 支付流
 */
public class PayFlowTrait implements FlowTrait {

    private PayRecordBean payRecordBean;

    private ActionRecorderStoreData actionRecorderStoreData;

    public PayFlowTrait(PayRecordBean payRecordBean) {
        this.payRecordBean = payRecordBean;
    }

    public PayRecordBean getPayRecordBean() {
        return payRecordBean;
    }

    public void setPayRecordBean(PayRecordBean payRecordBean) {
        this.payRecordBean = payRecordBean;
    }

    @Override
    public String flowId() {
        return payRecordBean.getOrderNo();
    }

    @Override
    public String currentState() {
        return payRecordBean.getStatus().name();
    }

    @Override
    public void changeState(String s) {
        payRecordBean.setStatus(PayStatus.valueOf(s));
    }

    @Override
    public int flowVersion() {
        return 0;
    }

    @Override
    public ActionRecorderStoreData getActionRecorderStoreData() {
        return actionRecorderStoreData;
    }

    @Override
    public void setActionRecorderStoreData(ActionRecorderStoreData actionRecorderStoreData) {
        this.actionRecorderStoreData = actionRecorderStoreData;
    }
}
