package compute;

import java.util.Random;

/**
 * description: 大正整数乘法
 * 改造后可支持负数
 * @author zhun.huang 2019-03-11
 */
public class BigMulti {

    public static String multi(String var1, String var2) {
        if (!BigAdder.isInteger(var1)) {
            throw new IllegalArgumentException("var1 is not a number");
        }
        if (!BigAdder.isInteger(var2)) {
            throw new IllegalArgumentException("var2 is not a number");
        }

        String result = "0";

        for (int i = var2.length() - 1; i >= 0; i--) {
            int incr = 0;
            String thisAdder = "";
            for (int j = var1.length() - 1; j >= 0; j--) {
                int consult = ((int) var1.charAt(j) - 48) * ((int) var2.charAt(i) - 48) + incr;
                incr = consult / 10;
                consult = consult % 10;
                thisAdder = thisAdder.concat(String.valueOf(consult));
            }
            if (incr!=0) {
                thisAdder = thisAdder.concat(String.valueOf(incr));
            }
            // reverse
            thisAdder = BigAdder.reverse(thisAdder);
            // 移位
            for (int j = 0; j < var2.length()-i-1; j++) {
                thisAdder = thisAdder.concat("0");
            }
            result = BigAdder.add(result, thisAdder);
        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
        long a = Math.abs(random.nextInt());
        long b = Math.abs(random.nextInt());
         if (! multi(String.valueOf(a), String.valueOf(b)).equals(String.valueOf(a * b))) {
             throw new RuntimeException("test fail");
         }
        System.out.println(multi(String.valueOf(a), String.valueOf(b)));
        }
    }
}
