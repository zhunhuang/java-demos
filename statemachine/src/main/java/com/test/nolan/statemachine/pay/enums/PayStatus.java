package com.test.nolan.statemachine.pay.enums;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午3:35
 * @email: nolan.zhun@gmail.com
 * @description:
 *
 */
public enum  PayStatus {
    PAY(0,"支付中"),
    PAY_SUCCESS(1,"支付成功"),
    REFUND(2,"退款中"),
    UNFREEZE(5,"解冻中"),
    REFUND_SUCCESS(12,"退款成功"),
    UNFREEZE_SUCCESS(20,"解冻成功"),
    GUARANTEE_SUCCESS(30,"担保成功"),
    GUARANTEE_CONFIRM(31,"担保扣款"),
    GUARANTEE_CONFIRM_SUCCESS(39,"担保扣款成功"),
    GUARANTEE_REVOKE(40,"担保撤销"),
    GUARANTEE_REVOKE_SUCCESS(42,"担保撤销成功");

    private int code;
    private String desc;

    PayStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
