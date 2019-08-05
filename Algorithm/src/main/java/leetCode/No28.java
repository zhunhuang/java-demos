package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No28 {
    public int strStr(String haystack, String needle) {
        if (haystack == null
                || needle == null
                || needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean found = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return i;
            }
        }
        return -1;
    }
}
