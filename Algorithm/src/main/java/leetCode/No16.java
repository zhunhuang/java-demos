package leetCode;

import java.util.Arrays;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No16 {

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length - 1; i++) {

            int L = i - 1;
            int R = i + 1;
            do {
                int tmpSum = nums[L] + nums[i] + nums[R];
                if (Math.abs(Long.valueOf(target) - tmpSum) < Math.abs(Long.valueOf(target) - min)) {
                    min = tmpSum;
                }
                if (tmpSum > target) {
                    L--;
                } else if (tmpSum < target) {
                    R++;
                } else {
                    break;
                }

            } while (L >= 0 && R < nums.length);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new No16().threeSumClosest(new int[]{1,1,1,1},3));
    }
}
