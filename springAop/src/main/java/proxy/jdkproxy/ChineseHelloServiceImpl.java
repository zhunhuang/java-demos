package proxy.jdkproxy;

/**
 * description: 业务代码, 实现类
 *
 * @author zhun.huang 2019-02-24
 */
public class ChineseHelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好，朋友");
    }
}
