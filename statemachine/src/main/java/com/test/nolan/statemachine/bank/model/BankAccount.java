package com.test.nolan.statemachine.bank.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午2:42
 * @email: nolan.zhun@gmail.com
 * @description: 银行账户model, 简单起见, accountId=bankCode
 */
public class BankAccount {

    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);


    private String bankCode;

    private int remainNum;

    private int frezzeNum;

    private Map<String, BankPayRecordModel> payRecordBeans = new HashMap<>();

    public BankAccount(String bankCode, int remainNum, int frezzeNum) {
        this.bankCode = bankCode;
        this.remainNum = remainNum;
        this.frezzeNum = frezzeNum;
    }

    public String getAccountId() {
        return bankCode;
    }

    public void setAccountId(String bankCode) {
        this.bankCode = bankCode;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    public int getFrezzeNum() {
        return frezzeNum;
    }

    public void setFrezzeNum(int frezzeNum) {
        this.frezzeNum = frezzeNum;
    }

    public BankPayRecordModel getPayRecordBean(String transactionId) {
        return payRecordBeans.get(transactionId);
    }

    public boolean addPayRecordBean(BankPayRecordModel payRecordBean) {
        if (payRecordBean == null || StringUtils.isBlank(payRecordBean.getTransactionId())) {
            logger.error("addPayRecordBean fail, param error");
            return false;
        }
        payRecordBeans.put(payRecordBean.getTransactionId(), payRecordBean);
        return true;
    }


    public boolean updatePayRecordBean(BankPayRecordModel payRecordBean) {
        if (payRecordBean == null || StringUtils.isBlank(payRecordBean.getTransactionId())) {
            logger.error("updatePayRecordBean fail, param error");
            return false;
        }
        if (payRecordBeans.get(payRecordBean.getTransactionId()) == null) {
            logger.error("updatePayRecordBean fail, payRecordBean not found");
            return false;
        }
        payRecordBeans.put(payRecordBean.getTransactionId(), payRecordBean);
        return true;

    }
}
