package register;

import definition.PoolResource;
import definition.PoolResourceType;
import definition.PoolResourcesHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 * 手动注册要监控的资源
 *
 * @author zhun.huang 2019-03-20
 */
public class ManualRegister {

    public static void register(PoolResourceType type, Object target) {
        PoolResource poolResource = new PoolResource();
        poolResource.setPoolResourceType(type);
        poolResource.setTarget(target);
        PoolResourcesHolder.addResourcesStats(poolResource);

        switch (type) {
            case ThreadPoolExecutor: {
                if(((ThreadPoolExecutor)target).getThreadFactory() instanceof BasicThreadFactory) {
                    BasicThreadFactory factory = (BasicThreadFactory) ((ThreadPoolExecutor) target).getThreadFactory();
                    poolResource.setName(factory.getNamingPattern());
                }
                return;
            }
            default: {

            }
        }
    }
}
