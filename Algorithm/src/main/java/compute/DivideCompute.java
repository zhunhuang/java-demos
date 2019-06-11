package compute;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

/**
 * description:
 * create: 2018-07-03
 * 通过位运算和逻辑运算模拟整数除法，使用二进制手算除法
 * 类似于十进制手算除法
 *
 * @author zhun.huang
 */
public class DivideCompute {

    public static int INT_SIZE = 32;

    public static void main(String[] args) {
        for (int j = 0; j < 1; j++) {
            int divident = 123;
            int divider = 19;
            if (divider == 0) {
                continue;
            }
            int consult = 0;
            int remainder = 0;
            for (int i = INT_SIZE - 1; i >= 0; i--) {
                remainder = remainder<<1;
                consult = consult<<1;
                System.out.println("第" + i + "位的值是： " + (((divident >> i) & 1)));
                int newDivident = remainder + ((divident >> i) & 1);
                System.out.println(newDivident);
                if (newDivident == 0) {
                    continue;
                }
                if (newDivident > divider) {
                    consult = consult + 1;
                    remainder = newDivident - divider;
                } else if (newDivident == divider) {
                    consult = consult + 1;
                    remainder = 0;
                } else {
                    consult = consult + 0;
                    remainder = remainder + ((divident >> i) & 1);
                }
                System.out.println("当前被除数: "+newDivident+"    商: "+ consult + "    余数: " + remainder + "   32位坐标:" + i);
            }
            System.out.println("计算：" + divident + " / " + divider + " = " + consult + "......" + remainder);
            int rightConsult = divident / divider;
            int rightRemainder = divident % divider;
            System.out.println("结果：" + divident + " / " + divider + " = " + rightConsult + "......" + rightRemainder);
        }
    }
}
