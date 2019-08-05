package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No7 {

    public int reverse(int x) {
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        if (sb.toString().startsWith("-")) {
            sb = new StringBuilder(String.valueOf(x).substring(1)).reverse();
            if (0L - Long.valueOf(sb.toString()) < Integer.MIN_VALUE) {
                return 0;
            } else {
                return 0 - Integer.valueOf(sb.toString());
            }
        } else {
            StringBuilder reverse = sb.reverse();
            if (Long.valueOf(reverse.toString()) > Integer.MAX_VALUE) {
                return 0;
            } else {
                return Integer.valueOf(reverse.toString());
            }
        }
    }
}
