package com.test.nolan.statemachine.order.enums;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午7:27
 * @email: nolan.zhun@gmail.com
 * @description: 订单流
 */
public enum OrderStatus {
    BOOK_OK(0,"订房成功等待支付"),
    PAY_OK(1,"支付成功等待酒店确认"),
    CONFIRM_OK(2,"酒店确认,锁定房源"),
    CANCEL_OK(12,"订单取消"),
    APPLY_SYSTEM_REFUNDMENT(30,"退款待运营确认"),
    APPLY_HOTEL_REFUNDMENT(31,"退款待酒店确认"),
    WAIT_REFUNDMENT(32,"待退款"),
    REFUND_OK(39,"退款完成"),
    APPLY_4_RETURN_PAY(50, "未锁定房源申请退款"),
    NONE(100, "");

    private int code;
    private String desc;

    OrderStatus(int code, String desc) {
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
