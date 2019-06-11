package com.test.nolan.statemachine.order.bean;

import com.test.nolan.statemachine.order.enums.OrderStatus;

import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午7:57
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class HotelOrderBean {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 状态
     */
    private OrderStatus status;

    /**
     * 预定的酒店id
     */
    private String hotelId;

    /**
     * 预定的酒店入住时间
     */
    private Date reserveDate;

    /**
     * 入住时长(天)
     */
    private int dayNum;

    /**
     * 支付金额
     */
    private int payPrice;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "HotelOrderBean{" +
                "orderNo='" + orderNo + '\'' +
                ", status=" + status +
                ", hotelId='" + hotelId + '\'' +
                ", reserveDate=" + reserveDate +
                ", dayNum=" + dayNum +
                ", payPrice=" + payPrice +
                ", createTime=" + createTime +
                '}';
    }
}
