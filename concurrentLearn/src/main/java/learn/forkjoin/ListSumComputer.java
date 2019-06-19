package learn.forkjoin;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * description:
 * 对N个数求和，假设每次只能两个数相加，且进行一次加法运算十分耗时。
 * <p>
 * 1. 使用fork join pool 和java8 stream 对可并行的任务进行性能测试
 * parallelStream 底层使用的就是 forkJoinPool
 * parallelStream 和自己动手实现的forkJoin耗时是差不多的。
 * 2. 假设需要计算的次数是n次, 每一次耗时是
 * @author zhun.huang 2018-11-14
 */
public class ListSumComputer extends RecursiveTask<Long> {

	private List<Integer> list;

	private static int granularitySize = 10;

	public ListSumComputer(List<Integer> list) {
		this.list = list;
	}

	@Override
	protected Long compute() {
		if (list.size() <= granularitySize) {
			return (long) list.stream().reduce(0, ListSumComputer::add);
		}
		int medium = list.size() / 2;
		ListSumComputer leftSumComputer = new ListSumComputer(list.subList(0, medium));
		ListSumComputer rightSumComputer = new ListSumComputer(list.subList(medium, list.size()));
		leftSumComputer.fork();
		rightSumComputer.fork();
		return leftSumComputer.join() + rightSumComputer.join();
	}

	public static void main(String[] args) {
		ArrayList<Integer> core = Lists.newArrayList(1, 3, 124, 23434, 4545, 124, 35, 35, 35, 235);
		ArrayList<Integer> integers = new ArrayList<>();
		ListSumComputer listSumComputer = new ListSumComputer(integers);
		System.out.println("第一次耗时忽略掉, 底层可能有新建线程等操作...");
		for (int i = 0; i < 30; i++) {
			integers.addAll(core);
			System.out.println("总计算量:"+ integers.size() + ", 每一次计算耗时大约3ms, 串行耗时为: " + (integers.size()*3));
			long start = System.currentTimeMillis();
			Long compute = listSumComputer.compute();
			long gap1 = System.currentTimeMillis();
			Integer reduce = integers.parallelStream().reduce(0, ListSumComputer::add);
			long gap2 = System.currentTimeMillis();
			Integer reduce2 = integers.stream().reduce(0, ListSumComputer::add);
			long gap3 = System.currentTimeMillis();
			System.out.println("fork join pool 耗时: " + (gap1 - start));
			System.out.println("parallelStream 耗时" + (gap2 - gap1));
			System.out.println("stream 耗时" + (gap3 - gap2));
		}
	}

	public static int add(int a, int b) {
		// mock 耗时操作
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a + b;
	}
}
