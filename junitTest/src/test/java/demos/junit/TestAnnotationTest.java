package demos.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 测试@Test注解。
 * 两个参数： 测试执行时间限制，预期异常限制
 */
@RunWith(JUnit4.class)
public class TestAnnotationTest {

    @Test(timeout = 101,expected = Test.None.class)
    public void test1() throws InterruptedException {
        Thread.sleep(100);
    }
}
