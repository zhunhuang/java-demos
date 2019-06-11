package com.test.nolan.statemachine.pay.bean;

import com.test.nolan.statemachine.pay.enums.PayStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午3:50
 * @email: nolan.zhun@gmail.com
 * @description: 支付记录bean
 */
public class PayRecordBean implements Serializable {

    /**
     * 自增主键
     */
    private long id;

    /**
     * 支付单号
     */
    private String orderNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 需要支付的金额
     */
    private int payPrice;

    /**
     * 需要退款的金额
     */
    private int refundPrice;

    /**
     * 需要担保的金额
     */
    private int guaranteePrice;

    /**
     * 需要担保扣款金额
     */
    private int guaranteeConfirmPrice;

    /**
     * 需要担保撤销金额
     */
    private int guaranteeRevokePrice;


    /**
     * (业务线)内部交易流水号
     */
    private String innerTransId;

    /**
     * 分账公式
     */
    private String shareData;

    /**
     * 支付状态
     */
    private PayStatus status;

    /**
     * 用户支付时间
     */
    private Date paidTime;

    /**
     * 卡类型
     */
    private String pmCode;

    /**
     * 支付网关
     */
    private String tppCode;

    /**
     * 银行卡
     */
    private String bankCode;

    /**
     * 业务线
     */
    private String busyTypeId;

    /**
     * 商户号
     */
    private String merchantCode;

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 外部交易流水号
     */
    private String transactionId;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getGuaranteeConfirmPrice() {
        return guaranteeConfirmPrice;
    }

    public void setGuaranteeConfirmPrice(int guaranteeConfirmPrice) {
        this.guaranteeConfirmPrice = guaranteeConfirmPrice;
    }

    public int getGuaranteeRevokePrice() {
        return guaranteeRevokePrice;
    }

    public void setGuaranteeRevokePrice(int guaranteeRevokePrice) {
        this.guaranteeRevokePrice = guaranteeRevokePrice;
    }

    public String getInnerTransId() {
        return innerTransId;
    }

    public void setInnerTransId(String innerTransId) {
        this.innerTransId = innerTransId;
    }

    public String getShareData() {
        return shareData;
    }

    public void setShareData(String shareData) {
        this.shareData = shareData;
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

    public String getPmCode() {
        return pmCode;
    }

    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBusyTypeId() {
        return busyTypeId;
    }

    public void setBusyTypeId(String busyTypeId) {
        this.busyTypeId = busyTypeId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
