package com.test.my;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhun.huang
 * @create: 2018-05-01 下午12:57
 * @email: nolan.zhun@gmail.com
 * @description: 线程池传递线程变量工具类
 */
public final class ThreadLocalHelper {

    private static Map<Thread, Map<String, Object>> threadMapMap = new ConcurrentHashMap<>();

    static Map<String, Object> getThreadMap() {
        return threadMapMap.get(Thread.currentThread());
    }

    static void setThreadMap(Map<String, Object> threadMap) {
        ThreadLocalHelper.threadMapMap.put(Thread.currentThread(), threadMap == null ? new HashMap<>() : threadMap);
    }

    static void removeThreadMap() {
        threadMapMap.remove(Thread.currentThread());
    }

    public static Object getThreadLocal(String key) {
        Map<String, Object> threadMap = getThreadMap();
        return threadMap == null ? null : threadMap.get(key);
    }

    public static void setThreadLocal(String key, Object value) {
        Map<String, Object> threadMap = threadMapMap.computeIfAbsent(
                Thread.currentThread(), k -> new HashMap<>());
        threadMap.put(key, value);
    }
}
