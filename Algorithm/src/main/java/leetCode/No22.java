package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No22 {

    public List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backTrace("", n, 0, 0);
        return result;
    }

    public void backTrace(String combination, int n, int leftCir, int right) {
        if (combination.length() == n * 2) {
            result.add(combination);
            return;
        }
        if (leftCir < n) {
            backTrace(combination.concat("("), n, leftCir + 1, right);
        }
        if (right < n) {
            backTrace(combination.concat(")"), n, leftCir, right + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new No22().generateParenthesis(2));
    }
}
