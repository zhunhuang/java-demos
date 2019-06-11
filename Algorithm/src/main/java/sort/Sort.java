package sort;

import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2018-11-12
 */
public interface Sort {

    <T extends Comparable<? super T>> void sort(List<T> list);

    <T> void sort(List<T> list, Comparator<T> comparator);
}
