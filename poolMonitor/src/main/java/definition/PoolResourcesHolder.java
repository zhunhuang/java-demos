package definition;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class  PoolResourcesHolder {

    /**
     * 持有资源池的弱引用，help gc
     */
    private static ConcurrentHashMap<PoolResourceType, List<WeakReference<PoolResource>>> registeredResourceMap = new ConcurrentHashMap<>();

    public static void addResourcesStats(PoolResource poolResource) {
        List<WeakReference<PoolResource>> list = registeredResourceMap.get(poolResource.getPoolResourceType());
        if (list == null) {
            list = new ArrayList<>();
            registeredResourceMap.putIfAbsent(poolResource.getPoolResourceType(), list);
        }
        list.add(new WeakReference<>(poolResource));
    }

    public static ConcurrentHashMap<PoolResourceType, List<WeakReference<PoolResource>>> getRegisteredResourceMap() {
        return registeredResourceMap;
    }

    public static void setRegisteredResourceMap(ConcurrentHashMap<PoolResourceType, List<WeakReference<PoolResource>>> registeredResourceMap) {
        PoolResourcesHolder.registeredResourceMap = registeredResourceMap;
    }

    public static List<PoolResource> getAllResourcesStats() {
        return registeredResourceMap
                .values()
                .stream()
                .flatMap(Collection::stream)
                .map(Reference::get)
                .collect(Collectors.toList());
    }

    /**
     * 弱引用测试
     * @param args
     */
    public static void main(String[] args) {

        Bean bean = new Bean();
        WeakReference<ExecutorService>  weakReference = new WeakReference<>(bean.executorService);

        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
        bean = null;
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    public static class Bean {
        public ExecutorService executorService = Executors.newSingleThreadExecutor();

    }

}
