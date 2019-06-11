import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 * 用来说明依赖注入的好处. 可以实现松耦合.
 * 依赖于接口, 通过构造器注入不同的实现类, 从而达到松耦合的目的.任何实现了该接口的类都可以被注入进去
 * 通过mock接口, 可以方便的测试:到底是被测试的服务所依赖的服务出现了问题,还是被测试的服务本身有问题.
 */
public class DITest {

    @Test
    public void testQuery() {
        QueryNameService mockQueryNameService = mock(QueryNameService.class);
        doReturn("nolan").when(mockQueryNameService).getName("1");
        MyQueryService myQueryService = new MyQueryService(mockQueryNameService);
        String s = myQueryService.saHello("1");
        System.out.println(s);
    }
}
