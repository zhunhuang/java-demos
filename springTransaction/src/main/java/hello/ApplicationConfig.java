package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
public class ApplicationConfig {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        TransactionTestService transactionTestService = (TransactionTestService) context.getBean("transactionTestService");
        try {
            transactionTestService.save("nolan", true);
        } catch (Exception e) {
            System.out.println(e);
        }
        String password = transactionTestService.selectByName("nolan");
        System.out.println("密码为：" + password);
    }
}
