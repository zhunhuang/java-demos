package sort.practice;

import sort.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-12
 */
public class SelectSort implements Sort {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            T MIN = list.get(i);
            int minIndex = i;
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j).compareTo(MIN) < 0) {
                    MIN = list.get(j);
                    minIndex = j;
                }
            }
            T tmp = list.get(i);
            list.set(i,list.get(minIndex));
            list.set(minIndex,tmp);
        }
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
