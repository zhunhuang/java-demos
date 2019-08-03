package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No6 {

    public static String convert(String s, int numRows) {
        if (s.length()<=1 || numRows==1) {
            return s;
        }
        int length = Math.min(s.length(),numRows);
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            rows.add("");
        }
        int j =0;
        int i = 0;
        boolean down = true;
        while (j<s.length()) {
            rows.set(i,rows.get(i).concat(s.charAt(j)+""));
            if (down) {
                i++;
            } else {
                i--;
            }
            if (i==0 || i==length-1) {
                down = !down;
            }
            j++;
        }
        StringBuilder result = new StringBuilder();
        for (int k = 0; k < length; k++) {
            result.append(rows.get(k));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("AB",1));
    }
}
