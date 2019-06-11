package com.test.nolan.statemachine.hotel.role;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午7:34
 * @email: nolan.zhun@gmail.com
 * @description: mock 酒店客服
 */
@Component
public class HotelSupportStuff {

    @Scheduled(cron = "*/23 * * * * *")
    public void scheduleHotel(){

    }

}
