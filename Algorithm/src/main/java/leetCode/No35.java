package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-06
 */
public class No35 {
    public int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length-1;

        while (start<=end) {
            int midium = (start+end)/2;
            if (nums[midium] == target) {
                return midium;
            }else if (nums[midium] > target) {
                end = midium-1;
            } else if (nums[midium] < target) {
                start =midium+1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println(new No35().searchInsert(new int[]{1,3,5,6},0));
        System.out.println(new No35().searchInsert(new int[]{1,3,5,6},1));
        System.out.println(new No35().searchInsert(new int[]{1,3,5,6},3));
        System.out.println(new No35().searchInsert(new int[]{1,3,5,6},5));
    }
}
