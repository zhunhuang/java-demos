package sort.impl;

import sort.Sort;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.swap;

/**
 * description:
 *
 * @author zhun.huang 2018-11-12
 */
public class InsertSort implements Sort {

    public <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i+1; j > 0; j--) {
                if (list.get(j).compareTo(list.get(j-1))>0) {
                    swap(list,j,j-1);
                } else {
                    break;
                }
            }
        }
    }

    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
