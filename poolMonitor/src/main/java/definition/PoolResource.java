package definition;

import java.util.Objects;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class PoolResource {

    private Object target;

    private String name;

    private PoolResourceType poolResourceType;

    private boolean statsInProgress;

    private ResourceStats resourceStats;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PoolResourceType getPoolResourceType() {
        return poolResourceType;
    }

    public void setPoolResourceType(PoolResourceType poolResourceType) {
        this.poolResourceType = poolResourceType;
    }

    public boolean isStatsInProgress() {
        return statsInProgress;
    }

    public void setStatsInProgress(boolean statsInProgress) {
        this.statsInProgress = statsInProgress;
    }

    public ResourceStats getResourceStats() {
        return resourceStats;
    }

    public void setResourceStats(ResourceStats resourceStats) {
        this.resourceStats = resourceStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PoolResource that = (PoolResource) o;
        return Objects.equals(target, that.target) &&
                poolResourceType == that.poolResourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, poolResourceType);
    }
}
