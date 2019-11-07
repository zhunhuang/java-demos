package context;

import config.Xmlconfig;
import definition.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-30 下午4:14
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String, Object> ioc = new HashMap<>();

    private Map<String, BeanDefinition> config;

    @Override
    public Object getBean(String beanName) {
        return ioc.get(beanName);
    }

    public ClassPathXmlApplicationContext(String path) {
        try {
            config = Xmlconfig.getConfig(path);
        } catch (Exception e) {
            throw new RuntimeException("get bean xml config fail");
        }
        if (config != null) {
            for (Map.Entry<String, BeanDefinition> entry : config.entrySet()) {
                String beanId = entry.getKey();
                if (ioc.containsKey(beanId)) {
                    throw new RuntimeException("duplicated bean definition for " + beanId);
                }
                BeanDefinition beanDefinition = entry.getValue();
                Object object = registerBean(beanDefinition);
                ioc.put(beanId, object);
            }
        }
    }

    private Object registerBean(BeanDefinition beanDefinition) {
        try {
            Class cl = Class.forName(beanDefinition.getClassName());
            return cl.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(beanDefinition.getClassName() + " not found");
        }
    }

//    private void initBean() {
//        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
//            Object object = entry.getValue();
//            BeanDefinition beanDefinition = config.get(entry.getKey());
//            beanDefinition.getPropertyDefinitionList();
//            for (PropertyDefinition propertyDefinition : beanDefinition.getPropertyDefinitionList()) {
//                propertyDefinition.getName();
//            }
//            PropertyDescriptor sourcePd = getPropertyDescriptor(object.getClass(), name);
////得到get方法
//            Method readMethod = sourcePd.getWriteMethod();
//            return readMethod;
//        }
//    }

}
