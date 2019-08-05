package sort.practice;

        import sort.Sort;

        import java.util.Arrays;
        import java.util.Comparator;
        import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-08-01
 */
public class HeapSort implements Sort {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        T[] objects = (T[]) list.toArray();
        heapSort(objects, list.size());
        list.clear();
        list.addAll(Arrays.asList(objects).subList(0, list.size()));
    }

    @Override
    public <T> void sort(List<T> list, Comparator<T> comparator) {
    }

    <T extends Comparable<? super T>> void maxHeapFixdown(T[] array, int i, int count) {

        int j;
        // 注意这里的调整，不用通过一直交换，为啥呢？ 因为只有i节点位置不对，其余的位置顺序都是对的，只需要其余位置上浮
        // 再将i节点放到匹配到的位置就行
        T temp = array[i];
        // i为父节点，j=2*i+1即为左子节点。2*i+2即为右子节点
        j = 2 * i + 1;
        while (j < count) {
            if (j + 1 < count && array[j + 1].compareTo(array[j]) > 0) j++; // 找出较大的子节点

            if (array[j].compareTo(temp) < 0) break; // 如果较大的子节点都比父节点小, 表示位置找到了。直接返回

            array[i] = array[j]; // 设置父节点为较大节点
            i = j; // 调整的子节点作为新一轮的父节点
            j = 2 * i + 1; // 调整的子节点的子节点
        }
        // 将i节点的值放到所匹配到的位置上
        array[i] = temp;
    }

    <T extends Comparable<? super T>> void heapSort(T[] array, int count) {

        for (int i = (count - 1) / 2; i >= 0; i--) {
            // 构造大顶堆
            maxHeapFixdown(array, i, count);
        }

        for (int i = count - 1; i >= 1; i--) {

            // 交换根结点与最末节点
            T temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            // 剩余的n-1个元素再次建立小顶堆
            maxHeapFixdown(array, 0, i);
        }

    }

    static void heapSort(int[] array, int count) {
        // 构造小顶堆
        for (int i = count-1; i >= 0; i--) {
            minHeapFixDown(array, i, count);
        }

        for (int i = count - 1; i > 0; i--) {
            // 交换根节点与最末尾的节点，根节点是最小的值。则此时数组的最后一个值就是小顶堆。
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;

            // 剩余的元素再构建一次小顶堆。 此时array[0]则又是小顶堆了
            minHeapFixDown(array, 0, i);
        }

    }

    // 前提： 这个array已经满足了 除了 i节点，i的子节点们都已经符合小顶堆的情况
    // 结果： 基于上述前提，将 i节点也进行了正确的分配。
    static void minHeapFixDown(int[] array, int i, int count) {
        int tmp = array[i];
        int j = i * 2 + 1;

        while (j < count) {
            // 找到左右子树中更小的
            if (j + 1 < count && array[j] > array[j + 1]) j++;
            // 最小的都比父节点大，表名已经构建完了
            if (array[j] > tmp) break;

            // 否则， 需要进行继续安排这个父亲节点
            array[i] = array[j];
            i = j;
            j = 2 * j + 1;
        }
        array[i] = tmp;
    }

    public static void main(String[] args) {
        int[] array  = new int[]{3,2,5,4,1};
        heapSort(array,5);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
