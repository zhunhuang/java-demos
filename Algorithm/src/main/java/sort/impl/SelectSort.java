package sort.impl;

import sort.Sort;

import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2018-11-12
 */
public class SelectSort implements Sort {
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int record = i;
            T tmp = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(i)) > 0) {
                    list.set(i,list.get(j));
                    record = j;
                }
            }
            list.set(record, tmp);
        }
    }

    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
