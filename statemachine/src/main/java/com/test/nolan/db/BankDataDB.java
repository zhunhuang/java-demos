package com.test.nolan.db;

import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:15
 * @email: nolan.zhun@gmail.com
 * @description: 模拟db
 */
@Repository
public class BankDataDB {
    private Map<String, PayRecordBean> dataMap = new HashMap<>();

    public void putData(PayRecordBean payRecordBean) {
        dataMap.put(payRecordBean.getOrderNo(), payRecordBean);
    }

    public PayRecordBean getData(String flowId) {
        return dataMap.get(flowId);
    }
}
