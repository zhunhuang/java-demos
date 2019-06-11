package definition;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class ResourceStats {

    private TaskStats taskStats;

    private long activeCount;

    private long corePoolSize;

    private long maxPoolSize;

    private long currentPoolSize;

    private long currentQueueSize;

    private long maxQueueSize;

    private Class queueType;


    public TaskStats getTaskStats() {
        return taskStats;
    }

    public void setTaskStats(TaskStats taskStats) {
        this.taskStats = taskStats;
    }

    public long getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(long activeCount) {
        this.activeCount = activeCount;
    }

    public long getCurrentQueueSize() {
        return currentQueueSize;
    }

    public void setCurrentQueueSize(long currentQueueSize) {
        this.currentQueueSize = currentQueueSize;
    }

    public long getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(long corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public long getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(long maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public long getCurrentPoolSize() {
        return currentPoolSize;
    }

    public void setCurrentPoolSize(long currentPoolSize) {
        this.currentPoolSize = currentPoolSize;
    }

    public Class getQueueType() {
        return queueType;
    }

    public void setQueueType(Class queueType) {
        this.queueType = queueType;
    }

    public long getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(long maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    @Override
    public String toString() {
        return "{\"ResourceStats\":{"
                + "\"taskStats\":" + taskStats
                + ", \"activeCount\":\"" + activeCount + "\""
                + ", \"corePoolSize\":\"" + corePoolSize + "\""
                + ", \"maxPoolSize\":\"" + maxPoolSize + "\""
                + ", \"currentPoolSize\":\"" + currentPoolSize + "\""
                + ", \"currentQueueSize\":\"" + currentQueueSize + "\""
                + ", \"maxQueueSize\":\"" + maxQueueSize + "\""
                + ", \"queueType\":" + queueType
                + "}}";
    }
}
