package sort.practice;

import sort.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-03-12
 */
public class MergeSort implements Sort {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        List<T> sorted = mergeSort(list);
        for (int i = 0; i < sorted.size(); i++) {
            list.set(i, sorted.get(i));
        }
    }

    private <T extends Comparable<? super T>> List<T> mergeSort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }
        int midiumIndex = list.size() / 2;

        return merge(mergeSort(list.subList(0, midiumIndex)), mergeSort(list.subList(midiumIndex, list.size())));
    }

    private <T extends Comparable<? super T>> List<T> merge(List<T> list1, List<T> list2) {
        int i = 0, j = 0;
        List<T> merged = new ArrayList<>();
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).compareTo(list2.get(j)) < 0) {
                merged.add(list1.get(i));
                i++;
            } else {
                merged.add(list2.get(j));
                j++;
            }
        }
        while (i < list1.size()) {
            merged.add(list1.get(i));
            i++;
        }
        while (j < list2.size()) {
            merged.add(list2.get(j));
            j++;
        }
        return merged;
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
