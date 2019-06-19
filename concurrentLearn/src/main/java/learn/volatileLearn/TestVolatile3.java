package learn.volatileLearn;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

/**
 * description:
 * create: 2018-08-23
 * volatile 不保证原子性的测试
 * @author zhun.huang
 */
public class TestVolatile3 {

	public static volatile int a = 0;
	public static int b = 0;
	public static LongAdder c = new LongAdder();

	public static void main(String[] args) throws IOException, InterruptedException {
		new Thread(new Adder()).start();
		new Thread(new Adder()).start();
		new Thread(new Adder()).start();
		new Thread(new Adder()).start();
		new Thread(new Adder()).start();
		Thread.sleep(1000);
		System.out.println("a:" + a);
		System.out.println("b:" + b);
		System.out.println("c:" + c.longValue());
//		System.in.read();
	}

	public static class Adder implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100000; i++) {
				a++;
				b++;
				c.increment();
			}
			System.out.println("计算结束" + Thread.currentThread().getName());
		}
	}
}
