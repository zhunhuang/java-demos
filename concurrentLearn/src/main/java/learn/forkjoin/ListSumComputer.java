package learn.forkjoin;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * description:
 * 对N个数求和，假设每次只能两个数相加，且进行一次加法运算十分耗时。
 *
 * 1. 使用fork join pool 和java8 stream 对可并行的任务进行性能测试
 * parallelStream 底层使用的就是 forkJoinPool
 * parallelStream 和自己动手实现的forkJoin耗时是差不多的。
 *
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
        ArrayList<Integer> integers = Lists.newArrayList(1, 3, 124, 23434, 4545, 124, 545, 345, 253, 325235, 35, 35, 35, 235
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 35, 2352, 23535, 4545, 566, 676, 87, 8, 98, 98, 9, 78465, 73, 64, 55, 325, 324, 534, 56, 456, 43, 6, 346, 246
                , 235, 23, 5, 35, 34, 6, 45, 675, 7, 57, 677, 54, 7, 547, 54, 7, 534, 5, 124, 1, 24, 12, 4, 14, 1);
        ListSumComputer listSumComputer = new ListSumComputer(integers);
        for (int i = 0; i < 10; i++) {

            long start = System.currentTimeMillis();
            Long compute = listSumComputer.compute();
            long gap1 = System.currentTimeMillis();
            System.out.println(compute);
            Integer reduce = integers.parallelStream().reduce(0, ListSumComputer::add);
//            Integer reduce = integers.stream().reduce(0, ListSumComputer::add);
            System.out.println(reduce);
            long gap2 = System.currentTimeMillis();
            System.out.println("fork join pool 耗时: " + (gap1 - start));
            System.out.println("stream 耗时" + (gap2 - gap1));
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
