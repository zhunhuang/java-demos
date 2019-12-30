package com.nolan.test.order;

import lombok.Data;

import java.util.Date;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/28
 */
@Data
public class OrderBean {

    private String orderNo;

    private String userName;

    private Integer tradeAmount;

    private Date orderTime;

}
