package base.spring;

import com.spring.learn.service.HelloService;
import com.spring.learn.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * 使用spring-test + mock
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-application.xml")
public class SpringMockTest {

    @InjectMocks
    @Resource
    private HelloService helloService;

    @Mock
    UserService userService;

    @Before
    public void setup() {
        // 作用是注入和初始化被mock的对象。这里是指userService
        MockitoAnnotations.initMocks(this);
        when(userService.getUserName(anyInt())).thenReturn("nolan");
    }

    @Test
    public void test() {
        String helloMsg = helloService.sayHello(1);
        System.out.println(helloMsg);
        Assert.assertNotNull(helloMsg);
    }

}
