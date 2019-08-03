package recursion;

/**
 * description:
 * f(0)=1
 * f(1)=1
 * f(n) = f(n-1)+f(n-2)
 * @author zhun.huang 2019-08-01
 */
public class FibonacciDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.println(proposedFibonacci(i));
        }
    }

    /**
     * 1,1,2,3,5,8,13,21,34,55,89
     * 很慢， 为啥， 因为重复计算了啊。
     */

    public static int fibonacci(int n) {
        if (n==0) {
            return 1;
        }
        if (n==1) {
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int[] arr = new int[100];

    /**
     * 快，因为缓存了结果啊。
     */
    public static int proposedFibonacci(int n) {
        if (n==0) {
            arr[0] = 1;
            return arr[0];
        }
        if (n==1) {
            arr[1] = 1;
            return arr[1];
        }
        arr[n] = arr[n-1] + arr[n-2];
        return arr[n];
    }
}
