package data.structure.loopArray.usage1;

import java.util.Random;

/**
 * description:
 *
 * @author zhun.huang 2019-03-08
 */
public class TestThis {

    public static void main(String[] args) {
        Queue<Integer> q1 = new ArrayImpl<>(10);
        Queue<Integer> q2 = new LoopArrayImpl<>(10);
        Queue<Integer> q3 = new ListImpl<>();
        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            int nextInt = random.nextInt();
            q1.push(nextInt);
            q2.push(nextInt);
            q3.push(nextInt);
        }
        for (int i = 0; i < 9; i++) {
            q1.pop();
            q2.pop();
            q3.pop();
            System.out.println(q1.length());
            System.out.println(q2.length());
            System.out.println(q3.length());
        }
        System.out.println("go on");
        for (int i = 0; i < 9; i++) {
            int nextInt = random.nextInt();
            q3.push(nextInt);
            q2.push(nextInt);
            System.out.println(q3.pop());
            System.out.println(q2.pop());
        }

        System.out.println("go on");
        for (int i = 0; i < 9; i++) {
            q1.push(i);
            System.out.println(q1.pop());
        }
    }
}
