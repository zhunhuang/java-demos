package demos.junit;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 跳过某个测试,
 * 和直接不测试的不同是，这样注解会计入测试统计结果。
 */
@RunWith(JUnit4.class)
public class IgnoreAnnotationTest {

    @Test
    public void test1(){

    }

    @Ignore
    @Test
    public void test3(){
        System.out.println("test3");
    }
}
