package com.test.nolan.db;

import com.test.statemachine.core.model.OperationLog;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:15
 * @email: nolan.zhun@gmail.com
 * @description: 模拟db
 */
@Repository
public class LogDataDB {

    private Map<Integer, List<String>> dataMap = new HashMap<>();

    public void putLog(OperationLog operationLog) {
        List<String> logs = dataMap.get(operationLog.getSecurityLevel());
        String log = "执行人: " + operationLog.getExecutorInfo().getExecutor() + ", 执行日志: {}" + operationLog.getContent();
        if (CollectionUtils.isEmpty(logs)) {
            ArrayList<String> newLogs = new ArrayList<>();
            newLogs.add(log);
            dataMap.put(operationLog.getSecurityLevel(), newLogs);
        } else {
            logs.add(log);
        }
    }

    public List<String> getData(int securityLevel) {
        return dataMap.get(securityLevel);
    }
}
