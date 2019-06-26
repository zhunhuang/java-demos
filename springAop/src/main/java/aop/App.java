package aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.HelloService;

/**
 * description:
 * create: 2018-09-16
 *
 * @author zhun.huang
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:spring-application.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.sayHello("nolan");
//        SimpleDriverDataSource dataSource = (SimpleDriverDataSource)context.getBean("dataSource");
//        try {
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * from ADMIN_USER");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("name"));
//                System.out.println(resultSet.getString("password"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
