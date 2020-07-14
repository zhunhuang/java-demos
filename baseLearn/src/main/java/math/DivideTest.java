package math;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * description:
 *
 * @author zhunhuang, 2020/6/24
 */
public class DivideTest {


    public static void main(String[] args) {
        long a = 123456789L;
        System.out.println(getByPosition(a,5));
        System.out.println(getByPosition2(a,5));
        System.out.println(setByPosition(a,5,1));
        System.out.println(setByPosition2(a,5,1));
        System.out.println(Lists.newArrayList(1,2,3,4));
    }

    public static long getByPosition(long a, int pos) {
        if (pos<1) {
            throw new IllegalArgumentException("下标从1开始,但实际输入是" + pos);
        }
        return a/(int)Math.pow(10, pos-1) %10;
    }

    public static long setByPosition(long a, int pos, int val) {
        if (pos<1) {
            throw new IllegalArgumentException("下标从1开始,但实际输入是" + pos);
        }

        int posDivide = (int) Math.pow(10, pos);
        long leftValue = a/ posDivide * posDivide;
        long rightValue = a % (posDivide/10);

        int posVal = val * posDivide /10;
        return leftValue + posVal + rightValue;
    }

    public static long getByPosition2(long a, int pos) {
        if (pos<1) {
            throw new IllegalArgumentException("下标从1开始,但实际输入是" + pos);
        }
        String strA = String.valueOf(a);
        return Long.parseLong(String.valueOf(strA.charAt(strA.length()-pos)));
    }

    public static long setByPosition2(long a, int pos, int val) {
        if (pos<1) {
            throw new IllegalArgumentException("下标从1开始,但实际输入是" + pos);
        }
        char[] chars = String.valueOf(a).toCharArray();
        chars[chars.length-pos] = String.valueOf(val).charAt(0);
        return Long.parseLong(String.valueOf(chars));
    }
}

