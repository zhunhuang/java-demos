package common;

/**
 * description:
 * f(i) 表示 从1-i 这i个整数中， 出现的1的个数，比如f(1)=1, f(2)=1, f(13)=(6)(1,,10,11,12,13)
 *
 * 同样是利用缓存法， 缓存之前的计算结果。
 * @author zhun.huang 2019-08-01
 */
public class FindFNequalsN {

    public static int[] arr = new int[10000];

    public static int cursivef(int n) {
        if (n==0) {
            arr[0] = 0;
            return arr[0];
        }
        if (n==1) {
            arr[1] = 1;
            return arr[1];
        }
        String strN = String.valueOf(n);
        int count = 0;
        for (char c : strN.toCharArray()) {
            if (c=='1') {
                count++;
            }
        }
        arr[n] = arr[n-1] + count;
        return arr[n];
    }

    public static int f(int n) {
        for (int i = 0; i < n; i++) {
            cursivef(i);
        }
        return cursivef(n);
    }

    public static void main(String[] args) {
        System.out.println(f(1000));
    }

}
