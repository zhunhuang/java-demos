package com.test.nolan.statemachine.qbize.role;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午7:39
 * @email: nolan.zhun@gmail.com
 * @description: mock 平台运营
 */
@Component
public class SystemOperator {

    @Scheduled(cron = "*/12 * * * * *")
    public void scheduleHotel(){

    }
}
