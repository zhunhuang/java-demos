import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * description:
 *
 * @author zhunhuang, 2020/7/3
 */
public class MockService {

    static Logger LOGGER = LoggerFactory.getLogger(MockService.class);

    public static class HelloService {

        public static void sayHelloWithReflection(String name) {
            try {
                remoteServiceProxy.getIntNameWithReflection(name);
            } catch (Exception e) {
                LOGGER.error("远端获取用户名失败， 请求参数：{}, cause", name, e);
            }
        }

        public static void sayHelloWithReflectionExclude(String name) {
            try {
                remoteServiceProxy.getIntNameWithReflection(name);
            } catch (Exception e) {
                LOGGER.error("远端获取用户名失败， 请求参数：{}, cause", name, ExceptionUtil.filterReflectTrace(e));
            }
        }

        public static void sayHelloNoReflection(String name) {
            try {
                remoteServiceProxy.getIntNameNoReflection(name);
            } catch (Exception e) {
                LOGGER.error("获取用户名失败， 请求参数：{}, cause", name, e);
            }
        }

        public static void sayHelloApacheUtil(String name) {
            try {
                remoteServiceProxy.getIntNameNoReflection(name);
            } catch (Exception e) {
                LOGGER.error("获取用户名失败， 请求参数：{}, cause:{}", name, ExceptionUtils.getStackTrace(e));
            }
        }

        public static void sayHelloPrintNoException(String name) {
            try {
                remoteServiceProxy.getIntNameWithReflection(name);
            } catch (Exception e) {
                LOGGER.error("获取用户名失败， 请求参数：{}", name);
            }
        }
    }


    /**
     * 远端服务客户端
     */
    private static class remoteServiceProxy {

        private static Method getNameMethod;

        static {
            try {
                getNameMethod = RemoteServiceMock.class.getMethod("getName", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        public static int getIntNameWithReflection(String name) throws Exception {
            try {
                return (int) getNameMethod.invoke(RemoteServiceMock.class, name);
            } catch (Exception e) {
                throw e;
            }
        }

        public static int getIntNameNoReflection(String name) throws Exception {
            try {
                return RemoteServiceMock.getName(name);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    /**
     * 真实的远端服务
     */
    private static class RemoteServiceMock {

        public static int getName(String name) {
            return Integer.parseInt(name);
        }
    }
}
