package test;

import context.ClassPathXmlApplicationContext;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/4
 */
public class TestThis {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        TestService testService = (TestService)context.getBean("testService");
        System.out.println(testService.sayHello());
    }
}
