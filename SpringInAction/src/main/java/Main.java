import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MyQueryService myQueryService = context.getBean(MyQueryService.class);
        myQueryService.saHello("1");
    }
}
