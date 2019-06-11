package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author: zhun.huang
 * @create: 2018-04-03 下午9:41
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("=====session do=======");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("=====session end=======");
    }
}
