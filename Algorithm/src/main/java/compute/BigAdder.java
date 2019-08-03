package compute;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * description: 大正整数加法
 * 改造后可支持负数
 * @author zhun.huang 2019-03-11
 */
public class BigAdder {

    public static String add(String var1, String var2) {
        if (!isInteger(var1)) {
            throw new IllegalArgumentException("var1 is not a number");
        }
        if (!isInteger(var2)) {
            throw new IllegalArgumentException("var2 is not a number");
        }
        StringBuilder result = new StringBuilder();
        String added = var1.length()>var2.length()? var1:var2;
        String adder = var1.length()>var2.length()? var2:var1;
        int incr = 0;
        for (int i = 1; i <= adder.length(); i++) {
            int consult = added.charAt(added.length() - i)- '0' + adder.charAt(adder.length() - i) - '0' + incr;
            incr = consult/10;
            consult = consult%10;
            result = result.append(String.valueOf(consult));
        }
        for (int i = adder.length()+1; i <=added.length() ; i++) {
            int consult = added.charAt(added.length() - i)- '0' + incr;
            incr = consult/10;
            consult = consult%10;
            result = result.append(String.valueOf(consult));
        }
        if (incr==1) {
            result = result.append("1");
        }

        return result.reverse().toString();
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[\\+]?[\\d]*$");
        return pattern.matcher(str.trim()).matches();
    }

    public static String reverse(String var) {
        String result = "";
        for (int i = 0; i < var.length(); i++) {
            result = result.concat(String.valueOf(var.charAt(var.length()-i-1)));
        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            long i1 = Math.abs(random.nextInt());
            long i2 = Math.abs(random.nextInt());
            String add = add(String.valueOf(i1), String.valueOf(i2));
            if (!add.equals(String.valueOf(i1+i2))) {
                System.out.println("test fail");
            }
            System.out.println(add);
            System.out.println(i1+i2);
        }
    }
}
