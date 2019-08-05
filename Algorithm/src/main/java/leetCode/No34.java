package leetCode;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No34 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length ==0) {
            return new int[]{};
        }
        int top = nums[nums.length-1];
        int buttom = 0;
        int cursor = (top-buttom)/2;
        SortedSet<Integer> result = new TreeSet<>();
        while (top!=buttom) {
            if (nums[cursor] > target) {
                top = cursor;
            }
            if (nums[cursor] < target) {
                buttom = cursor;
            }
            if (nums[cursor] == target) {
                break;
            }
        }

    }

}
