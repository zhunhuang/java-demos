package leetCode;

import java.util.Arrays;
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
        int top = nums.length-1;
        int buttom = 0;
        int cursor = (top-buttom)/2;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        SortedSet<Integer> result = new TreeSet<>();
        while (top>=buttom) {
            if (nums[cursor] > target) {
                top = cursor-1;
            }
            if (nums[cursor] < target) {
                buttom = cursor+1;
            }
            if (nums[cursor] == target) {
                start = leftSearch(nums,buttom,cursor,target);
                end = rightSearch(nums,cursor,top,target);
                break;
            }
            cursor = (top + buttom)/2;
        }
        if (start>end) {
            return new int[]{};
        }
        return new int[]{start,end};
    }

    public int leftSearch(int[] nums,int buttom, int top, int target) {
        if (buttom>=top) {
            if (nums[top]==target) {
                return top;
            } else {
                return top+1;
            }
        }
        int i  = (buttom+top)/2;
        if (nums[i] < target) {
            return leftSearch(nums,i+1,top,target);
        }
        return leftSearch(nums,buttom,i-1,target);
    }

    public int rightSearch(int[] nums,int buttom, int top, int target) {
        if (buttom>=top) {
            if (nums[top]==target) {
                return top;
            } else {
                return top-1;
            }
        }
        int i  = (buttom+top)/2;
        if (nums[i] > target) {
            return rightSearch(nums,buttom,i,target);
        }
        return rightSearch(nums,i+1,top,target);
    }

    public static void main(String[] args) {
        System.out.println(new No34().searchRange(new int[]{2,2},2));
    }

}
