package base;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * description:
 *
 * @author zhunhuang, 2019/10/30
 */
public class BeanComparator {

    /**
     * 使用{@link Objects#equals(Object, Object)} 对比，不支持递归，仅支持最外层属性对比
     *
     * @param obj1 进行属性比较的对象1
     * @param obj2 进行属性比较的对象2
     * @return 属性差异比较结果
     */
    public static CompareResult compare(Object obj1, Object obj2) {
        return compare(obj1, obj2, null);
    }

    /**
     * 使用{@link Objects#equals(Object, Object)} 对比，不支持递归，仅支持最外层属性对比
     *
     * @param obj1            进行属性比较的对象1
     * @param obj2            进行属性比较的对象2
     * @param ignoreFieldList 选择忽略比较的属性列表
     * @return 属性差异比较结果
     */
    @SuppressWarnings("rawtypes")
    public static CompareResult compare(Object obj1, Object obj2, List<String> ignoreFieldList) {
        Map<String, List<Object>> diffMap = new HashMap<String, List<Object>>();
        if (obj1.getClass() == obj2.getClass()) { //只有两个对象都是同一类型才有可比性
            Class clazz = obj1.getClass();
            //获取object的属性描述
            PropertyDescriptor[] pds;
            try {
                pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            } catch (IntrospectionException e) {
                throw new RuntimeException("BeanComparator error,cause:", e);
            }
            for (PropertyDescriptor pd : pds) {
                try {
                    String name = pd.getName();
                    if (ignoreFieldList != null && ignoreFieldList.contains(name)) {
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();//获取属性的get方法
                    //在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    //在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);

                    // 1. equals方法相等则相等。
                    if (Objects.equals(o1, o2)) {
                        continue;
                    }
                    // 2. 实现了Comparable接口，判断compareTo是否相等
                    if (o1 instanceof Comparable && ((Comparable) o1).compareTo(o2) == 0) {
                        continue;
                    }

                    // 否则认为不相等。
                    List<Object> list = new ArrayList<>();
                    list.add(o1);
                    list.add(o2);
                    diffMap.put(name, list);
                } catch (Exception e) {
                    throw new RuntimeException("compare" + pd.getName() + "error, cause: ", e);
                }
            }
        }
        return new CompareResult(diffMap);
    }

    public static class CompareResult {

        private Map<String, List<Object>> diffMap = new HashMap<>();

        public CompareResult(Map<String, List<Object>> diffMap) {
            this.diffMap = diffMap;
        }

        public boolean isEqual() {
            return diffMap.isEmpty();
        }

        public Map<String, List<Object>> getDiffMap() {
            return diffMap;
        }

        public void setDiffMap(Map<String, List<Object>> diffMap) {
            this.diffMap = diffMap;
        }

        @Override
        public String toString() {
            return "{\"CompareResult\":{"
                    + "\"diffMap\":" + diffMap
                    + "}}";
        }
    }

}
