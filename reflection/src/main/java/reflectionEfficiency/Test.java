package reflectionEfficiency;

import java.lang.reflect.Method;

/**
 * @author: zhun.huang
 * @create: 2018-05-25 上午11:43
 * @email: nolan.zhun@gmail.com
 * @description: 结论: 阻塞方法, 复杂点的方法, 通过反射其调用的话, 性能是可以忽略的.
 * 非阻塞方法, 比如一个最简单的方法, 里面只有一个赋值操作, 通过反射去调用和非反射去调用, 在循环调用次数特别多的情况下, 性能最多慢100左右.
 * 总结: 反射调用方法(m.invoke()方法)和正常调用方法的性能是相当的.
 * 反射性能问题主要是:
 *      1. 多了getMethod()方法,不过这个在jdk7有一版优化,在jdk8应该也优化了不少.
 * 二: 关于反射调用的性能
 *  MethodAccessor实现有两个版本，一个是Java实现的，另一个是 native code实现的。
 *      Java实现的版本在初始化时需要较多时间，但长久来说性能较好，因为java代码允许jdk继续优化，即时编译之类的优化；
 *      native版本正好相反，启动时相对较快，但运行时间长了之后速度就 比不过Java版了
 *      开头若干次使用native版，等反射调用次数超过阈值时则生成 一个专用的MethodAccessor实现类
 */
public class Test {
    private static final int loopCnt = 1000 * 1000 * 500;
    public static void main(String[] args) throws Exception {
        System.out.println("java.version="+System.getProperty("java.version"));
        t1();
        t2();
        t3();
    }

    //每次重新生成对象
    public static void t1(){
        long s = System.currentTimeMillis();
        for (int i = 0; i < loopCnt; i++) {
            Person p = new Person();
            // age++防止被优化
            p.setAge(i++);
        }
        long e = System.currentTimeMillis();
        System.out.println("t1.time="+(e-s));
    }

    //同一个对象
    public static void t2(){
        long s = System.currentTimeMillis();
        Person p = new Person();
        for (int i = 0; i < loopCnt; i++) {
            // age++防止被优化
            p.setAge(i++);
        }
        long e = System.currentTimeMillis();
        System.out.println("t2.time="+(e-s));
    }

    public static void t3() throws Exception{
        long s = System.currentTimeMillis();
        Class<Person> c = Person.class;
        Person p = c.newInstance();
        Method m = c.getMethod("setAge", Integer.class);
        for (int i = 0; i < loopCnt; i++) {
            m.invoke(p, i++);
        }
        long e = System.currentTimeMillis();
        System.out.println("t3.time="+(e-s));
    }

    static class Person{
        private int age = 20;
        public int getAge() {
            return age;
        }
        public void setAge(Integer age) {
            for (int i = 0; i < 10; i++) {
                // age++防止被优化
                this.age = age++;
            }
        }
    }
}
