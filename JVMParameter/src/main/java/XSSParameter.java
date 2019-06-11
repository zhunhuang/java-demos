/**
 * description:
 * create: 2018-09-07
 * 参数识别大小写
 * -Xss 指定单个栈大小, 如: -Xss2m
 * jdk1.7,jdk1.8要求至少大于228k
 * jdk1.5以上默认1M
 * 结果:
 *      -Xss228k
 *
 * @author zhun.huang
 * tomcat 重定向 标准输出到catalina.out文件. 没有被业务代码catch的异常.
 */
public class XSSParameter {
    static int a = 0;
    static int b = 0;

    public static void main(String[] args) {
        try {
            testStackOverFlow();
        } catch (Exception e) {
            System.err.println("stackDepth: " + a);
        }
    }

    public static void testStackOverFlow() {
        a++;
        long b = 0L;
        testStackOverFlow();
    }

}
