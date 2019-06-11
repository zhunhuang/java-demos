package com.test;

import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2018-04-11 下午5:44
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class WrapRunnable implements Runnable {

    private Runnable runnable;

    private final Map<InheritableThreadLocal<?>, ?> copiedRef;

    public WrapRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.copiedRef = WrapThreadLocal.holder.get();
    }

    @Override
    public void run() {

        runnable.run();
    }
}
