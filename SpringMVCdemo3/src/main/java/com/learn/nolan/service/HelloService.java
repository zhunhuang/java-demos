package com.learn.nolan.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.LongAdder;

/**
 * description:
 *
 * @author zhun.huang 2018-12-17
 */
@Service
public class HelloService {

    private volatile LongAdder longAdder = new LongAdder();

    public HelloMsg sayHello() {
        longAdder.increment();
        HelloMsg helloMsg = new HelloMsg();
        helloMsg.setTotalCount(longAdder.longValue());
        return helloMsg;
    }

    public static class HelloMsg {
        private String msg = "你好，世界";

        private long totalCount = 0;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public long getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(long totalCount) {
            this.totalCount = totalCount;
        }
    }
}
