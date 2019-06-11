package com.test.nolan.statemachine.hotel.service;

import com.test.nolan.statemachine.common.service.MQMockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午11:42
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class HotelMockService {
    private static final Logger logger = LoggerFactory.getLogger(HotelMockService.class);

    private AtomicInteger hotelStock;
    private String hotelId = "rj_bj_123456";

    {
        hotelStock = new AtomicInteger(100);
    }

    @Resource
    private MQMockService mqMockService;

    public boolean hasStock() {
        return hotelStock.intValue() > 0;
    }

    public boolean deductStock() {
        if (hotelStock.decrementAndGet() > 0) {
            return true;
        } else {
            hotelStock.incrementAndGet();
            return false;
        }
    }

    public String reserveHotel(String orderNo) {
        if (hasStock() && deductStock()) {
            logger.info("用户预定成功,订单号: {} 酒店id:{}, 剩余库存: {}", orderNo, hotelId, hotelStock.intValue());
            return hotelId;
        } else {
            return "";
        }
    }

    public void confirmReserve(String orderNo) {
        if (hasStock()) {
            logger.info("酒店锁定房源,订单号: {} 酒店id:{}", orderNo, hotelId);
            mqMockService.mockConfirmHotelMessage(orderNo, "confirm");
        } else {
            mqMockService.mockConfirmHotelMessage(orderNo,"fail");
        }
    }
}
