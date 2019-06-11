import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
public class SpringXMLConfigTest {

    private ApplicationContext applicationContext;

    @Before
    public void doBefore() {
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    @Test
    public void TestQuery() {
        MyQueryService myQueryService = applicationContext.getBean(MyQueryService.class);
        myQueryService.saHello("4");
    }

}
