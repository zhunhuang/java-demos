package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: zhun.huang
 * @create: 2018-04-03 下午9:39
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("=====context - contextInitialized=======");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("=====context contextDestroyed=======");
    }
}
