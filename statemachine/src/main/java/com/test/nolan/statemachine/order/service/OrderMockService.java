package com.test.nolan.statemachine.order.service;

import com.test.nolan.statemachine.common.util.OrderGenerator;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.enums.OrderStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-15 下午11:50
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class OrderMockService {

    @Resource
    private OrderPort orderPort;


    public HotelOrderBean mockCreateOrder(String userName, String hotelId, Date time, int payPrice) {
        HotelOrderBean orderBean = new HotelOrderBean();
        String orderNo = OrderGenerator.generatOrderNo();
        orderBean.setUserName(userName);
        orderBean.setStatus(OrderStatus.BOOK_OK);
        orderBean.setPayPrice(payPrice);
        orderBean.setHotelId(hotelId);
        orderBean.setCreateTime(time);
        orderBean.setOrderNo(orderNo);
        orderBean.setReserveDate(new Date());
        orderBean.setDayNum(1);
        synchronized (this) {
            return orderPort.save(orderBean) ? orderBean : null;
        }
    }
}
