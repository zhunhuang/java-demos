package base.boot;

import com.spring.learn.BootStrap;
import com.spring.learn.service.HelloService;
import com.spring.learn.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 1. 仍然使用SpringJUnit4ClassRunner作为执行环境，支持 spring的注解。
 * 2. 使用SpringBootTest注解指定 boot上下文及其配置和指定web端口等。
 * 3. 使用@MockBean来实现对指定bean的mock。所有其他bean都该bean的依赖都会被mock。
 * 4。 使用Mockito来配置被mock的类的动作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootStrap.class)
public class BaseSpringBootTest {

    @MockBean
    private UserService userService;

    @Resource
    private HelloService helloService;

    @Before
    public void setup() {
        Mockito.when(userService.getUserName(Mockito.anyInt())).thenReturn("nolan");
    }

    @Test
    public void test() {
        String msg = helloService.sayHello(1);
        System.out.println(msg);
        Assert.assertNotNull(msg);
    }
}
