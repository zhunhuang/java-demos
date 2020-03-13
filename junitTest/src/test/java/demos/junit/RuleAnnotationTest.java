package demos.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class RuleAnnotationTest {

    @Rule
    public Timeout timeout = new Timeout(1000, TimeUnit.MILLISECONDS);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void test_failOnTimeOut() throws InterruptedException {
        Thread.sleep(10001);
    }

    @Test
    public void test_failOnException() {
        throw new RuntimeException("mock exception");
    }

    @Test
    public void test_success() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("mock exception");
        throw new RuntimeException("mock exception");
    }

}
