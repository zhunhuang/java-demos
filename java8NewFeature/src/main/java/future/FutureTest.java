package future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * description:
 * create: 2019-04-24
 *
 * @author zhun.huang
 */
public class FutureTest {

	public static ExecutorService executorService = Executors.newFixedThreadPool(30);

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("开始测试, 当前时间: " + startTime);
		long maxWaitTime = 1000;
		List<Future> futureList = new ArrayList<>();

		executorService.submit(new InterruptTask(Thread.currentThread()));
		executorService.submit(new CancelTask(futureList));

		for (int i = 0; i < 50; i++) {
			final Future<Integer> remoteRequestFuture = executorService.submit(new MockRemoteRequestCallable());
			futureList.add(remoteRequestFuture);
		}
		int i = 0;
		for (Future future : futureList) {
			try {
				final Integer integer = (Integer) future.get(maxWaitTime, TimeUnit.MILLISECONDS);
				System.out.println("获取到结果, 返回结果是:" + integer);
			} catch (CancellationException e) {
				System.out.println("远程执行结果被取消了");
			} catch (InterruptedException e) {
				System.out.println("远程执行结果被打断了, 没有进行执行");
			} catch (TimeoutException e) {
				System.out.println("执行结果超时了");
			} catch (ExecutionException e) {
				System.out.println("执行结果执行出错了");
			} finally {
				i++;
				System.out.println("当前" + i);
			}
		}

	}

	public static class InterruptTask implements Runnable {
		Thread thread;
		private long submitTime = System.currentTimeMillis();

		public InterruptTask(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			final long needSleepTime = 350 - (System.currentTimeMillis() - submitTime);
			if (needSleepTime > 0) {
				try {
					Thread.sleep(needSleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("当前时间: " + System.currentTimeMillis() + ". 等待时间超过950 ms, 将主线程打断");
			thread.interrupt();
		}
	}

	public static class CancelTask implements Runnable {

		List<Future> futureList;
		private long submitTime = System.currentTimeMillis();

		public CancelTask(List<Future> futureList) {
			this.futureList = futureList;
		}

		@Override
		public void run() {
			final long needSleepTime = 900 - (System.currentTimeMillis() - submitTime);
			if (needSleepTime > 0) {
				try {
					Thread.sleep(needSleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("当前时间: " + System.currentTimeMillis() + ". 等待时间超过900 ms, 将没有完成的任务cancel掉");
			for (Future future : futureList) {
				final boolean canceled = future.cancel(true);
				if (canceled) {
					System.out.println("终止一个任务");
				}
			}
		}
	}

	public static class MockRemoteRequestCallable implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			System.out.println("模拟远程访问");
			Random random = new Random();
//			final int waitTime = random.nextInt() % 1000;
			final int waitTime = Math.abs(random.nextInt() % 1000);
			Thread.sleep(waitTime);
			System.out.println("远程访问结果返回, 耗时:" + waitTime);
			return waitTime;
		}
	}
}
