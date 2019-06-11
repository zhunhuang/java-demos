package com.test;

import com.test.statemachine.api.ExecutorInfo;
import com.test.nolan.db.OrderDataDB;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.enums.OrderStatus;
import com.test.nolan.statemachine.order.service.OrderPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午10:23
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TestStatemachine {

    public static void main(String[] args) {
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring/root-context.xml");
        OrderPort orderPort = (OrderPort)ct.getBean("payPort");
        OrderDataDB orderDataDB = (OrderDataDB)ct.getBean("orderDataDB");
        System.out.println(ct.getApplicationName() + " is starting......");
        System.out.println("请输入订单号");
        Scanner scanner = new Scanner(System.in);
        String orderNo = scanner.nextLine();
        System.out.println("您当前输入的orderNo=" + orderNo);
        System.out.println("开始存储订单......");
        HotelOrderBean hotelOrderBean = new HotelOrderBean();
        hotelOrderBean.setOrderNo(orderNo);
        orderPort.save(hotelOrderBean);
        HotelOrderBean orderBean = orderDataDB.getData(orderNo);
        System.out.println("校验存储结果: " + orderBean);
        while (true) {
            System.out.println("请输入你想要改变的状态");
            String toStatus = scanner.nextLine();
            orderPort.changeOrderStatus(orderNo, OrderStatus.valueOf(toStatus), ExecutorInfo.ExecutorRole.USER);
        }
    }
}
