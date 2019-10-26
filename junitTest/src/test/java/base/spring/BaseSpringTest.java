package base.spring;

import com.spring.learn.service.DateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 1. 使用SpringJUnit4ClassRunner or SpringRunner作为执行环境，其支持
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-application.xml")
public class BaseSpringTest {

    @Resource
    private DateService dateService;

    @Test
    public void test() {
        String currentDate = dateService.currentDate();
        Assert.assertNotNull(currentDate);
    }

}
