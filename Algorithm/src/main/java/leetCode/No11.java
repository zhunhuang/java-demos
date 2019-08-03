package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No11 {
    public int maxArea(int[] height) {
        if (height == null || height.length<=1) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                int area = (j-i) * Math.min(height[j],height[i]);
                if (area>maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new No11().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    public int maxArea2(int[] height) {
        if (height == null || height.length<=1) {
            return 0;
        }
        int start = 0;
        int end = height.length-1;
        int maxArea = (end-start) * Math.min(height[start],height[end]);
        while (start<end) {
            maxArea = Math.max(maxArea,(end-start) * Math.min(height[start],height[end]));
            if (height[start]>height[end]) {
                end--;
            } else {
                start++;
            }
        }
        return maxArea;
    }
}
