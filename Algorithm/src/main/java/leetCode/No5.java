package leetCode;

/**
 * description:
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
 *
 * @author zhun.huang 2019-08-02
 */
public class No5 {


    public static void main(String[] args) {
        System.out.println(new No5().longestPalindrome("babad"));
    }



    public String longestPalindrome(String s) {

        String result = "";

        for (int i = 0; i < s.length(); i++) {
            String tmp1 = findE(i, s);
            String tmp2 = findO(i, s);
            if (tmp1.length() > result.length()) {
                result = tmp1;
            }
            if (tmp2.length() > result.length()) {
                result = tmp2;
            }
        }
        return result;

    }

    private String findO(int i, String s) {
        int j = 0;
        for (j = 0; i - j >= 0 && i + j + 1 < s.length(); j++) {
            if (s.charAt(i - j) != s.charAt(i + j + 1)) {
                break;
            }
        }
        return s.substring(i - j + 1, i + j + 1);
    }

    private String findE(int i, String s) {
        int j = 0;
        for (j = 0; i - j >= 0 && i + j < s.length(); j++) {
            if (s.charAt(i - j) != s.charAt(i + j)) {
                break;
            }
        }
        return s.substring(i - j + 1, i + j);
    }
}
