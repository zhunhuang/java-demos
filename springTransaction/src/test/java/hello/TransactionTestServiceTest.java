package hello;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TransactionTestServiceTest {

    private static TransactionTestService transactionTestService;

    @BeforeClass
    public static void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        transactionTestService = (TransactionTestService) context.getBean("transactionTestService");
    }

    @Test
    public void save_transactionCommit() throws SQLException {
        // given
        String name = RandomStringUtils.random(10);

        // when
        transactionTestService.save(name, false);

        // then
        String password = transactionTestService.selectByName(name);
        Assert.assertNotNull(password);
    }

    @Test
    public void save_transactionRollBack() throws SQLException {
        // given
        String name = RandomStringUtils.random(10);

        // when
        try {
            transactionTestService.save(name, true);
        } catch (Exception e) {

        }

        // then
        String password = transactionTestService.selectByName(name);
        Assert.assertNull(password);
    }
}