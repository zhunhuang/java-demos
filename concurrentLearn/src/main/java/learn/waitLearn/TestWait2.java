package learn.waitLearn;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description:
 * create: 2019-07-03
 *
 * @author zhun.huang
 */
public class TestWait2 {

	public static Queue<Paint> waitingDoctorQueue = new LinkedList<>();

	public static void main(String[] args) {
		final Integer[] lists = Arrays.copyOfRange(new Integer[]{1,2}, 1, 2);
		System.out.println(lists[0]);
	}


	public static class Paint implements Runnable {


		public String name;

		public boolean checked;

		public String checkResult;

		@Override
		public void run() {


		}
	}

	public static class Doctor implements Runnable {

		@Override
		public void run() {
			while (true) {
				synchronized (this) {
					Paint paint = callNextPaint();
					System.out.println("患者:" + paint.name + " 就诊中");
					if (paint.checked) {
						// 诊断需要2s
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("根据检查报告, 患者: " + paint.name
								+ " 的诊断情况是: " + paint.checkResult);
					} else {
						System.out.println("患者:" + paint.name + " 还没有做检查, 先去做检查吧!");
						this.notifyAll();
					}
					System.out.println("呼叫下一个患者");
				}
			}
		}

		private Paint callNextPaint() {
			return waitingDoctorQueue.poll();
		}
	}

	public static class CheckMachine implements Runnable {

		@Override
		public void run() {

		}
	}
}
