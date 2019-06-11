package sort.impl;

import sort.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * description:
 * 归并排序， 属于分治法
 * 稳定排序， 占用空间大
 * @author zhun.huang 2018-11-13
 */
public class MergeSort implements Sort {
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
        int medium = list.size() / 2;
        List<T> left = list.subList(0, medium);
        List<T> right = list.subList(medium, list.size());
        return merge(mergeSort(left), mergeSort(right));
    }


    private <T extends Comparable<? super T>> List<T> merge(List<T> left, List<T> right) {
        int i = 0, j = 0;
        List<T> merged = new ArrayList<T>();
        while (i < left.size()) {
            if (j >= right.size()) {
                break;
            }
            if (left.get(i).compareTo(right.get(j)) > 0) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }
        return merged;
    }

    public <T> void sort(List<T> list, Comparator<T> comparator) {

    }
}
