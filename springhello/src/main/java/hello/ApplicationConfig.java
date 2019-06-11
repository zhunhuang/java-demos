package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 * @SpringBootApplication 相当于 @Configuration、@EnableAutoConfiguration 、 @ComponentScan 三个的作用
 */
//↓↓↓↓↓↓@Configuration作用等同于于Spring的xml配置文件
@Configuration
//↓↓↓↓↓↓@ComponentScan表示扫描当前包(hello)下的所有类,注入到spring容器中
@ComponentScan
public class ApplicationConfig {

    @Bean
    MessageService mockMessageService() {
        return () -> "Hello World";
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();
    }
}
