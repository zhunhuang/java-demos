package sort.practice;

import sort.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-12
 */
public class BubbleSort implements Sort {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {

        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size()-1; j > i; j--) {
                if (list.get(j).compareTo(list.get(j-1))>0) {
                    T tmp = list.get(j);
                    list.set(j,list.get(j-1));
                    list.set(j-1, tmp);
                }
            }
        }
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
