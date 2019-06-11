package future;

import java.util.concurrent.*;

/**
 * description:
 * create: 2019-04-24
 *
 * @author zhun.huang
 */
public class BasicTest {

	private static ExecutorService executorService = Executors.newFixedThreadPool(1);

	public static void main(String[] args) {
		System.out.println("主线程获提交异步任务到线程池");
		final Future submit = executorService.submit(new HelloCallable());
		try {
			System.out.println("主线程开始阻塞等待异步执行结果");
			final Object result = submit.get();
			System.out.println("主线程获取到远程结果：" + result);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("主线程阻塞等待异步执行结果时异常：" + e);
		}
	}

	public static class HelloCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			System.out.println("开始执行异步任务");
			Thread.sleep(1000);
			System.out.println("异步任务执行结束");
			return "hello world! from remote";
		}
	}
}
