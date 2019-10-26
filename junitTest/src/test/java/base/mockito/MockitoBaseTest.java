package base.mockito;

import com.spring.learn.dao.UserDao;
import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

/**
 * 使用最基本的JUnit4.class作为执行环境，支持
 * {@link BeforeClass}\
 * {@link org.junit.Before}\
 * {@link Test}\
 * {@link org.junit.After}\
 * {@link org.junit.AfterClass}
 * 等注解。
 * 缺点，必须手动将mock的类注入到被测试的类中。
 */
@RunWith(JUnit4.class)
public class MockitoBaseTest {


    /**
     * 被测试的类
     */
    private UserService userService;

    /**
     * 执行前，设置mock策略
     */
    @Before
    public void setup(){
        UserDao userDao = Mockito.mock(UserDao.class);

        User user = new User();user.setAge(12);user.setId(1);user.setName("nolan");

        Mockito.when(userDao.queryById(1)).thenReturn(user);
        userService = new UserService(userDao);
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
