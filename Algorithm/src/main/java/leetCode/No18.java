package leetCode;

import java.util.*;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);

        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Set<String> reduce = new HashSet<>();

        for (int i = 0; i < length; i++) {

            for (int j = i+2; j < length-1; j++) {
                int L = j-1;
                int R = j+1;

                do {
                    int tmpSum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (tmpSum==target) {
                        String tmp = String.valueOf(nums[i]) + String.valueOf(nums[L]) + String.valueOf(nums[j]) + String.valueOf(nums[R]);
                        if (reduce.contains(tmp)) {
                            L--;
                            R++;
                            continue;
                        }
                        reduce.add(tmp);
                        result.add(Arrays.asList(nums[i], nums[L], nums[j], nums[R]));
                        L--;
                        R++;
                        continue;
                    } else if (tmpSum>target) {
                        L--;
                    } else {
                        R++;
                    }
                } while (L>i && R< length);
            }
        }
        return result;
    }
}
