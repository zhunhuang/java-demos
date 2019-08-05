package sort.practice;

/**
 * description:
 * 找到最大的第K个元素
 *
 * @author zhun.huang 2019-08-03
 */
public class TopK {

    public int findTopKByMaxHeap(int[] nums, int k) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustToMaxHeap(nums, nums.length, i);
        }
        for (int i = nums.length - 1; i >=nums.length-k; i--) {
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;

            adjustToMaxHeap(nums, i, 0);
        }
        return nums[nums.length - k];
    }

    private void adjustToMaxHeap(int[] nums, int length, int i) {
        int j = 2 * i + 1;
        while (j < length) {
            if (j + 1 < length && nums[j] < nums[j + 1]) j++;
            if (nums[i] > nums[j]) break;

            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
            i = j;
            j = j * 2 + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new TopK().findTopKByMaxHeap(new int[]{4, 9, 2, 3, 1, 7, 6, 5, 8,10}, 3));
    }
}
