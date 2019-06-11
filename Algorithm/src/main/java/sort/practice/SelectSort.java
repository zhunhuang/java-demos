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
        for (int i = 0; i < list.size()-1; i++) {
            T max = list.get(i);
            int maxIndex = i;
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j).compareTo(max)>=0) {
                    max = list.get(j);
                    maxIndex = j;
                }
            }
            T tmp = list.get(i);
            list.set(i,max);
            list.set(maxIndex,tmp);
        }
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
