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

    private  <T extends Comparable<? super T>> List<T> mergeSort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }
        int middle = list.size() / 2;
        List<T> list1 = mergeSort(list.subList(0, middle));
        List<T> list2 = mergeSort(list.subList(middle, list.size()));
        return merge(list1, list2);
    }

    private <T extends Comparable<? super T>> List<T> merge(List<T> list1, List<T> list2) {
        List<T> merged = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < list1.size()) {
            if (j>=list2.size()) {
                break;
            }
            if (list1.get(i).compareTo(list2.get(j)) <= 0) {
                merged.add(list2.get(j));
                j++;
            } else {
                merged.add(list1.get(i));
                i++;
            }
        }
        while (i<list1.size()) {
            merged.add(list1.get(i++));
        }

        while (j<list2.size()) {
            merged.add(list2.get(j++));
        }
        return merged;
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
