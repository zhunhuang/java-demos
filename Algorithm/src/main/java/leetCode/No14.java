package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No14 {

    public String longestCommonPrefix(String[] strs) {

        int i = 0;
        String common = "";
        if (strs== null || strs.length == 0) {
            return common;
        }
        while (true) {
            if (strs[0].length()<=i) {
                break;
            }
            char c = strs[0].charAt(i);
            boolean match  =true;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length()<=i) {
                    return common;
                }
                if (c != strs[j].charAt(i)) {
                    return common;
                }
            }
                common = common.concat(String.valueOf(c));
            i++;
        }
        return common;
    }

    public static void main(String[] args) {
        System.out.println(new No14().longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
