package helper.util;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AopTestUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/3
 */
public class SpringBeanMockUtil {


    public static void setMockBean(Object target, String fieldName, Object toInjectMockBean) {
        Object realTarget = AopTestUtils.getUltimateTargetObject(target);
        setFieldValue(realTarget, fieldName, toInjectMockBean);
    }

    public static <T> void restoreSpringBeans(Object target) {
        Class clazz = target.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Resource resourceAnnotation = declaredField.getAnnotation(Resource.class);
            Autowired autoWiredAnnotation = declaredField.getAnnotation(Autowired.class);
            // 该字段为spring bean注解字段, 则进行还原
            if (resourceAnnotation != null || autoWiredAnnotation != null) {
                restoreSpringBean(target, declaredField.getName(), declaredField.getType());
            }
        }
    }

    public static <T> void restoreSpringBean(Object target, String fieldName, Class<T> clazz) {
        Object springBean;
        // 如果是被AOP代理的类，则获取真实的bean
        Object realTarget = AopTestUtils.getUltimateTargetObject(target);
        try {
            springBean = SpringUtils.getBean(fieldName);
        } catch (Exception e) {
            springBean = SpringUtils.getBean(clazz);
        }
        setFieldValue(realTarget, fieldName, springBean);
    }

    private static void setFieldValue(Object target, String fieldName, Object fieldValue) {
        Class clazz = target.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, fieldValue);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("未找到field:" + fieldName + "请确认filedName是否在 fieldList中:" + Lists.newArrayList(clazz.getDeclaredFields()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("set error, cause:", e);
        }
    }

    private static Object getFieldValue(Object target, String fieldName) {
        Class clazz = target.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(target);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("未找到field:" + fieldName + "请确认filedName是否在 fieldList中:" + Lists.newArrayList(clazz.getDeclaredFields()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("get error, cause:", e);
        }
    }

}
