package definition;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public enum PoolResourceType {

    /**
     * indicate the resource is an instance of {@link java.util.concurrent.ThreadPoolExecutor}
     */
    ThreadPoolExecutor,
    /**
     * indicate the resource is an instance of {@link wrapper.ManagedThreadPool}
     */
    ManagedThreadPool;

    PoolResourceType() {
    }
}
