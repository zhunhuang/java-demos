package register;

import definition.PoolResourceType;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * description:
 * 基于Spring框架自动注册的资源
 *
 * @author zhun.huang 2019-03-20
 */
public class SpringFramworkRegister implements ApplicationListener<ContextRefreshedEvent> {

    public void register(PoolResourceType type, Object target) {
        // todo
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //todo
    }
}
