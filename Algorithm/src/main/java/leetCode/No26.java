package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length==0)
            return 0;

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i]!=nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
