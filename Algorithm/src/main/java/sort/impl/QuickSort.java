package sort.impl;

import sort.Sort;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.swap;

/**
 * description:
 * 不稳定排序
 * 最差情况就是每次选择的标兵不是最大的就是最小的，此时复杂度是O(n^2)
 * @author zhun.huang 2018-11-12
 */
public class QuickSort implements Sort {
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        // 这里注意是闭区间
        quickSort(list, 0, list.size()-1);
    }

    public <T extends Comparable<? super T>> void quickSort(List<T> list, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(list, start, end);
            quickSort(list, start, partitionIndex-1);
            quickSort(list, partitionIndex + 1, end);
        }
    }

    private <T extends Comparable<? super T>> int partition(List<T> list, int start, int end) {
        int index = start;
        T observer = list.get(start);
        // 遍历，比我大是吧？你是第x个比我大的，那你跟第x位交换吧, 我是第0位，我TM先不动.
        for (int i = start + 1; i <= end; i++) {
            if (list.get(i).compareTo(observer) > 0) {
                index++;
                swap(list, i, index);
            }
        }
        // 遍历完啦， 有n个比我大的，都已经在前n位了吧， 那第n位你站第0位去，我站你那里，那前边就都是比我大的了,我的位置可以不用动了。
        swap(list,start,index);
        // 标兵就是我了，把我的位置汇报上去吧
        return index;
    }

    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
