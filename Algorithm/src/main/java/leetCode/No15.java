package leetCode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No15 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        boolean allZero = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0) {
                allZero =false;
                break;
            }
        }
        if (allZero) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(Arrays.asList(0,0,0));
            return result;
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<String> reduce = new HashSet<>();
        for (int i = 1; i < nums.length - 1; i++) {

            int L = i - 1;
            int R = i + 1;
            do {
                int tmpSum = nums[L] + nums[i] + nums[R];
                if (tmpSum == 0) {
                    String tmp = String.valueOf(nums[L]) + String.valueOf(nums[i]) + String.valueOf(nums[R]);

                    if (reduce.contains(tmp)) {
                        L--;
                        R++;
                        continue;
                    }
                    reduce.add(tmp);
                    result.add(Arrays.asList(nums[L], nums[i], nums[R]));
                    L--;
                    R++;
                    continue;
                }
                if (tmpSum > 0) {
                    L--;
                } else {
                    R++;
                }

            } while (L >= 0 && R < nums.length);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new No15().threeSum(new int[]{3,0,-2,-1,1,2}));
    }
}
