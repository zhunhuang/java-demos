package com.test.my;

import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2018-04-11 下午5:44
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class WrapRunnable implements Runnable {

    private Runnable runnable;

    private final Map<String,Object> copiedRef;

    public WrapRunnable(Runnable runnable) {
        this.runnable = runnable;
        copiedRef = ThreadLocalHelper.getThreadMap();
    }

    @Override
    public void run() {
        beforeRun();
        try {
            runnable.run();
        } finally {
            afterRun();
        }
    }

    private void beforeRun() {
        ThreadLocalHelper.setThreadMap(copiedRef);
    }

    private void afterRun() {
        ThreadLocalHelper.removeThreadMap();
    }
}
