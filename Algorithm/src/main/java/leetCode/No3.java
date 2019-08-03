package leetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * description:
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * @author zhun.huang 2019-08-02
 */
public class No3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring2("pwwkew"));
        ;
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        Set<Character> tmp;
        int tmpCount = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (tmpCount > max) {
                max = tmpCount;
            }
            tmpCount = 0;
            tmp = new HashSet<>();
            for (int j = i; j < chars.length; j++) {
                if (tmp.contains(chars[j])) {
                    break;
                }
                tmp.add(chars[j]);
                tmpCount++;
            }
            if (tmpCount > max) {
                max = tmpCount;
            }
        }
        return max;
    }

    // 滑动窗口解题

    public static int lengthOfLongestSubstring2(String s) {
        int max = 0;
        Set<Character> result = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            while (result.contains(s.charAt(i))) {
                result.remove(0);
            }
            result.add(s.charAt(i));
            if (result.size() > max) {
                max = result.size();
            }
        }

        return max;
    }
}
