package base.mockito;

import com.spring.learn.dao.UserDao;
import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * 使用MockitoRule方法， 实现对{@link Mock}、{@link InjectMocks}注解的支持。
 * 类似使用
 */
@RunWith(JUnit4.class)
public class MockitoBetter2Test {

    /**
     * 初始化mock对象
     */
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;

    @Before
    public void setup(){
        User user = new User();user.setAge(12);user.setId(1);user.setName("nolan");
        Mockito.when(userDao.queryById(1)).thenReturn(user);
    }


    /**
     * 执行测试，正常情况
     */
    @Test
    public void getUserNameTest(){
        String s = userService.getUserName(1);
        System.out.println(s);
        Assert.assertNotNull(s);
    }

    /**
     * 执行测试，未找到用户情况
     */
    @Test(expected = RuntimeException.class)
    public void getUserNameNotFoundTest(){
        String s = userService.getUserName(2);
        System.out.println(s);
        Assert.assertNull(s);
    }
}
