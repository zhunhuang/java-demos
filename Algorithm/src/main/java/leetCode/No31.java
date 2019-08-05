package leetCode;

import java.util.Arrays;

/**
 * description:
 * 下一个排列， 比当前大的下一个排列。
 * 从右往左找， 找到第一个左边的数a[i-1]， 小于右边的数a[i]。
 * 接着，将这个数a[i-i]，和这个数右边 i->n 之间比这个数大最少的一个数字进行交换。
 * 此时，a[1]-a[i-1] 这个高位就已经比排列的这个数大了。 但我们需要的是比这个数大的最小的那个数。
 * 因此， 我们再把a[i] - a[n] 之间从小到大再重排一次就好了。
 *
 *
 * @author zhun.huang 2019-08-04
 */
public class No31 {

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int x = -1;
        boolean found = false;
        // 找到， 左边小于右边的一个数 的位置x。
        for (int i = length-1; i >0 ; i--) {
            if (nums[i]>nums[i-1]) {
                x = i-1;
                break;
            }
        }
        // 将右边的从小到达排列
        Arrays.sort(nums, x+1, length);
        int y = -1;
        if (x >-1) {
            int j;
            // 找到 刚好大于x处的值的位置y
            for (j= x+1; j < nums.length; j++) {
                if (nums[j]>nums[x]) {
                    y = j;
                    break;
                }
            }
            // 进行交换
            int tmp = nums[x];
            nums[x] = nums[y];
            nums[y] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1};
        new No31().nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
