package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author: zhun.huang
 * @create: 2018-04-03 下午9:36
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("=====request end=======");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("=====request do=======");
    }
}
