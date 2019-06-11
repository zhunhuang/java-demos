import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
@Configuration
public class SpringJavaConfigTest {

    @Bean
    public static MyQueryService myQueryService() {
        return new MyQueryService(queryNameService());
    }

    @Bean
    public static QueryNameService queryNameService() {
        return new QueryNameServiceImpl();
    }

    @Test
    public void testQuery(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfigTest.class);
        MyQueryService myQueryService = context.getBean(MyQueryService.class);
        myQueryService.saHello("100");
    }
}
