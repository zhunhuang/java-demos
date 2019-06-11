package helloAnnotation;


import helloAnnotation.annotations.LogAnnotation;

import java.lang.reflect.Method;

/**
 * description:
 *
 * @author zhun.huang 2018-12-12
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.testAnnotation();
    }

    @LogAnnotation(level = 2)
    private void testAnnotation() {
        Class<?> aClass = this.getClass();
        try {
            Method testAnnotation = aClass.getMethod("testAnnotation");
            LogAnnotation annotation = testAnnotation.getAnnotation(LogAnnotation.class);
            if (annotation != null) {
                System.out.println("got annotation");
                System.out.println("log level is:" + annotation.level());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
