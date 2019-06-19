package learn.volatileLearn;

import java.io.IOException;

/**
 * description:
 * create: 2019-06-19
 * volatile 保证可见性的测试
 *
 * @author zhun.huang
 */
public class TestVolatile4 {

	public static volatile boolean signalA = false;
	public static boolean signalB = false;

	public static void main(String[] args) throws IOException, InterruptedException {
		new Thread(new TaskA()).start();
		new Thread(new TaskB()).start();

		Thread.sleep(100);

		signalA = true;
		signalB = true;
		// taskA能正常停止, taskB不能正常停止
		System.out.println("发送关闭信号");
		System.in.read();
	}

	public static class TaskA implements Runnable {

		@Override
		public void run() {
			int a = 0;
			while (!signalA) {
				a++;
			}
			System.out.println("a:" + a);
		}
	}

	public static class TaskB implements Runnable {

		@Override
		public void run() {
			int b = 0;
			while (!signalB) {
				b++;
			}
			System.out.println("b:" + b);
		}
	}
}
