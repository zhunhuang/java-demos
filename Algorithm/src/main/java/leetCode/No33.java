package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No33 {
    public int search(int[] nums, int target) {
        if (nums.length==0) {
            return -1;
        }
        int length = nums.length;
        int leftMin = nums[0];
        int rightMax = nums[length -1];
        if (target<leftMin && target>rightMax) {
            return -1;
        }
        if (target == leftMin) {
            return 0;
        }
        if (target ==rightMax) {
            return length-1;
        }
        int cursor = (length-1);
        int top = length-1;
        int botton = 0;
        boolean targetInLeftSide = target > leftMin;
        while (top!=botton) {
            if (target == nums[cursor]) {
                return cursor;
            }
            boolean cursorInLeftSide = nums[cursor] > rightMax;
            if (target > nums[cursor]) {
                if (targetInLeftSide && cursorInLeftSide) {
                    botton = cursor;

                }else if (targetInLeftSide && !cursorInLeftSide) {
                    top = cursor;

                } else if (!targetInLeftSide && cursorInLeftSide) {
                    botton = cursor;

                }
                else if (!targetInLeftSide && !cursorInLeftSide) {
                    botton = cursor;

                }
            } else {
                if (targetInLeftSide && cursorInLeftSide) {
                    top = cursor;

                }
                else if (targetInLeftSide && !cursorInLeftSide) {
                    top = cursor;

                }
                else if (!targetInLeftSide && cursorInLeftSide) {
                    botton = cursor;

                }
                else if (!targetInLeftSide && !cursorInLeftSide) {
                    top = cursor;
                }
            }
            cursor = (botton+top)/2;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new No33().search(new int[]{4,5,6,7,0,1,2},3));
    }
}
