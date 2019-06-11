package learn.volatileLearn;

/**
 * @author: zhun.huang
 * @create: 2018-03-28 下午3:46
 * @email: nolan.zhun@gmail.com
 * @description: volatile 保证变量的
 */
public class TestVolatile2 {

    public static class VolatileFeaturesExample {
        /********volatile******/
        volatile long v1 = 0L;

        public void setV1(long l) {
            v1 = l;
        }

        public void getAndIncrementV1() {
            v1++;
        }

        public long getV1() {
            return v1;
        }

        /*******以下代码块在语义上和以上代码块等价*******/

        long v2 = 0L;

        public synchronized void setV2(long l) {
            v2 = l;
        }

        public void getAndIncrementV2() {       // 普通方法调用
            long temp = getV2();                // 调用已同步读方法
            temp += 1L;                         // 普通写操作
            setV2(temp);                        // 调用已同步写方法
        }

        public synchronized long getV2() {
            return v2;
        }
    }

}
