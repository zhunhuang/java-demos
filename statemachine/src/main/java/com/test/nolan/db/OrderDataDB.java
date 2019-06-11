package com.test.nolan.db;

import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午9:09
 * @email: nolan.zhun@gmail.com
 * @description: 模拟db
 */
@Repository
public class OrderDataDB {
    private Map<String, HotelOrderBean> dataMap = new HashMap<>();

    public void putData(HotelOrderBean orderBean) {
        dataMap.put(orderBean.getOrderNo(), orderBean);
    }

    public HotelOrderBean getData(String flowId) {
        return dataMap.get(flowId);
    }
}
