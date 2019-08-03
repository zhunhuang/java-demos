package common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * description:
 * 求素数
 * 利用缓存法， 缓存之前的计算结果。
 * @author zhun.huang 2019-08-01
 */
public class PrimeNumberDemo {

    public static List<Integer> primeList = Lists.newArrayList(2,3);

    public static boolean cursiveIsPrime(int n) {
        for (Integer integer : primeList) {
            if (n%integer==0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int n) {
        for (int i = 4; i < n; i++) {
            if (cursiveIsPrime(i)) {
                primeList.add(i);
            }
        }
        return cursiveIsPrime(n);
    }

    public static void main(String[] args) {

        System.out.println(isPrime(99));
        System.out.println(isPrime(100));
        System.out.println(isPrime(101));
        System.out.println(isPrime(102));
        System.out.println(isPrime(103));
        System.out.println(isPrime(104));
        System.out.println(isPrime(105));
        System.out.println(isPrime(106));
        System.out.println(isPrime(107));

    }
}
