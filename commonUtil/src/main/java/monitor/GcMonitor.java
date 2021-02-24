package monitor;

import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 只关注YGC
 * 发生YGC时，触发系统负载收集器
 */
public class GcMonitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(GcMonitor.class);
    @Autowired
    LoadCollector loadCollector;

    @PostConstruct
    public void init() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            if (!gcBean.getName().contains("ParNew")) {
                continue;
            }
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            emitter.addNotificationListener(new Listener(), new Filter(), HandBack.getInstance());
            LOGGER.info("注册{}垃圾回收监听器", gcBean.getName());
        }
    }

    private class Listener implements NotificationListener {
        private final long JVM_START_TIME = ManagementFactory.getRuntimeMXBean().getStartTime();
        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        @Override
        public void handleNotification(Notification notification, Object handback) {
            LOGGER.info("监听到gc发生");
            GarbageCollectionNotificationInfo info =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            if (info.getGcInfo().getDuration() > 100) {
                LOGGER.error("YGC时间超过100ms");
            }
            printGcInfo(info);
            collectLoad(info);
        }

        private void collectLoad(GarbageCollectionNotificationInfo info) {
            int threshold = 25;
            if (info.getGcInfo().getDuration() > threshold) {
                loadCollector.run();
            }
        }

        private void printGcInfo(GarbageCollectionNotificationInfo info) {
            LOGGER.info("[{}] ({}) [{}]", info.getGcName(), info.getGcCause(), info.getGcAction());
            GcInfo gcInfo = info.getGcInfo();
            LOGGER.info("{} start:{} end:{} real:{}", gcInfo.getId(),
                    dateFormat.format(new Date(JVM_START_TIME + gcInfo.getStartTime())),
                    dateFormat.format(new Date(JVM_START_TIME + gcInfo.getEndTime())),
                    gcInfo.getDuration());
            Map<String, MemoryUsage> beforeGc = gcInfo.getMemoryUsageBeforeGc();
            Map<String, MemoryUsage> afterGc = gcInfo.getMemoryUsageAfterGc();
            for (Map.Entry<String, MemoryUsage> entry : beforeGc.entrySet()) {
                LOGGER.info("{}:{}K->{}K", entry.getKey(), entry.getValue().getUsed() >> 10,
                        afterGc.get(entry.getKey()).getUsed() >> 10);
            }
        }
    }

    private static class Filter implements NotificationFilter {

        @Override
        public boolean isNotificationEnabled(Notification notification) {
            return notification.getType()
                    .equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION);
        }
    }

    private static class HandBack {

        private HandBack() {
        }

        private static HandBack instance = new HandBack();

        public static HandBack getInstance() {
            return instance;
        }
    }
}
