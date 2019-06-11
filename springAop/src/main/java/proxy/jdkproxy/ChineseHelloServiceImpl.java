package proxy.jdkproxy;

/**
 * description:
 *
 * @author zhun.huang 2019-02-24
 */
public class ChineseHelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好，朋友");
    }
}
