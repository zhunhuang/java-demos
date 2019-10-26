package demos.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 测试 before注解的用法
 * 每执行一次Test方法都会执行一次@before注解标注的方法
 */
@RunWith(JUnit4.class)
public class BeforeAnnotationTest {


    @Before
    public void setup(){
        System.out.println("调用 before");
    }

    @Test
    public void test1(){
        System.out.println("执行测试1");
    }

    @Test
    public void test2(){
        System.out.println("执行测试2");
    }
}
