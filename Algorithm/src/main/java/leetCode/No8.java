package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No8 {

    public static int myAtoi(String str) {
        str = str.trim();
        boolean below = false;
        if (str.startsWith("-")) {
            str = str.substring(1);
            below = true;
        } else if (str.startsWith("+")) {
            str = str.substring(1);
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {
                stringBuilder.append(str.charAt(i));
            } else {
                break;
            }
        }
        if (stringBuilder.toString().equals("")) {
            return 0;
        }
        int result = 0;
        try {
            result = Integer.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            if (below ) {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
        if (below) {
            return 0 - result;
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
    }
}
