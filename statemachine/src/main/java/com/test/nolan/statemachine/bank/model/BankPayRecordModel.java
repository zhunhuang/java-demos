package com.test.nolan.statemachine.bank.model;

import com.test.nolan.statemachine.pay.enums.PayStatus;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhun.huang
 * @create: 2017-12-15 上午11:22
 * @email: nolan.zhun@gmail.com
 * @description: 银行交易流水
 */
public class BankPayRecordModel {

    public static AtomicInteger idCount = new AtomicInteger(1);

    /**
     * 自增主键
     */
    private long id;

    /**
     * 转出银行卡号
     */
    private String bankCode;

    /**
     * 转入银行卡号
     */
    private String outerBankCode;

    /**
     * 交易流水号
     */
    private String transactionId;

    /**
     * 支付的金额
     */
    private int payPrice;

    /**
     * 退款的金额
     */
    private int refundPrice;

    /**
     * 担保的金额
     */
    private int guaranteePrice;

    /**
     * 担保撤销金额
     */
    private int guaranteeRevokePrice;

    /**
     * 支付状态
     */
    private PayStatus status;

    /**
     * 用户支付时间
     */
    private Date paidTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getOuterBankCode() {
        return outerBankCode;
    }

    public void setOuterBankCode(String outerBankCode) {
        this.outerBankCode = outerBankCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public int getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(int refundPrice) {
        this.refundPrice = refundPrice;
    }

    public int getGuaranteePrice() {
        return guaranteePrice;
    }

    public void setGuaranteePrice(int guaranteePrice) {
        this.guaranteePrice = guaranteePrice;
    }

    public int getGuaranteeRevokePrice() {
        return guaranteeRevokePrice;
    }

    public void setGuaranteeRevokePrice(int guaranteeRevokePrice) {
        this.guaranteeRevokePrice = guaranteeRevokePrice;
    }

    public PayStatus getStatus() {
        return status;
    }

    public void setStatus(PayStatus status) {
        this.status = status;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
