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
public class BubbleSort implements Sort {

    public <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).compareTo(list.get(j - 1)) > 0) {
                    swap(list, j, j - 1);
                }
            }
        }
    }

    public <T> void sort(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (comparator.compare(list.get(j), list.get(j - 1)) > 0) {
                    swap(list, j, j - 1);
                }
            }
        }
    }
}
