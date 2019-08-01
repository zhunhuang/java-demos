package sort.practice;

import sort.Sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-12
 */
public class QuickSort implements Sort {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    public <T extends Comparable<? super T>> void quickSort(List<T> list, int startIndex, int endIndex) {

        if (startIndex >= endIndex) {
            return;
        }
        T observer = list.get(startIndex);
        int position = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (list.get(i).compareTo(observer) < 0) {
                position++;
                Collections.swap(list, position, i);
            }
        }
        Collections.swap(list, position, startIndex);
        quickSort(list, startIndex, position-1);
        quickSort(list, position + 1, endIndex);
    }


    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
