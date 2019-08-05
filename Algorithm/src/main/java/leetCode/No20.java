package leetCode;

import java.util.*;

/**
 * description:
 *
 * @author zhun.huang 2019-08-05
 */
public class No20 {
    public boolean isValid(String s) {
        List<Character> leftValid = Arrays.asList('(','[','{');
        List<Character> rightValid =Arrays.asList(')',']','}');

        LinkedList<Character> leftCir = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (leftValid.contains(charAt)) {
                leftCir.add(charAt);
                continue;
            }
            if (rightValid.contains(charAt)) {
                if (leftCir.size()==0) {
                    return false;
                }
                if (leftValid.indexOf(leftCir.removeLast()) != rightValid.indexOf(charAt)) {
                    return false;
                }
            }
        }
        return leftCir.size()==0;
    }
}
