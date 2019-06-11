/**
 * description:
 *
 * 主题：
 * 这里主要是测试怎么用jdk动态代理实现在调用一个方法的前后都执行一遍特定的代码。比如在方法执行前打日志，在执行后记录方法执行性能等。
 *
 * Car接口定义了 drive方法
 * Audi类实现了Car接口
 * 在CarHandler中，定义了一个invocationHandler，定义了被代理方法在运行的时候，真正的运行逻辑。
 *
 * 在ProxyTest类中，描述了如何使用JDK动态代理去代理类，这里
 *
 *
 *
 *
 * @author zhun.huang 2018-12-17
 */
package jdkProxy;
