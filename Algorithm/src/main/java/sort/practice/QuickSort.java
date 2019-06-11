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
        if (endIndex - startIndex <= 0) {
            return;
        }
        // 选取第一个作为观察者
        T observer = list.get(startIndex);
        int smallerNumber = 0;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (observer.compareTo(list.get(i)) < 0) {
                smallerNumber++;
                Collections.swap(list, startIndex + smallerNumber, i);
            }
        }
        Collections.swap(list, startIndex + smallerNumber, startIndex);
        quickSort(list, startIndex, startIndex + smallerNumber - 1);
        quickSort(list, startIndex + smallerNumber + 1, endIndex);
    }


    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
