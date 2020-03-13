package demos.junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 测试 BeforeClass注解的用法
 * 只能标示静态方法。 类初始化时调用一次。
 *
 */
@RunWith(JUnit4.class)
public class BeforeClassAnnotationTest {


    @BeforeClass
    public static void setup(){
        System.out.println("调用 BeforeClass");
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
