package sort.practice;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * 基于《100次循环，30000长度随机数组》的时间复杂度实验结果：
 * 冒泡：224723748
 * 插入：64230413
 * 选择：77930737
 * 归并：734385
 * 快速：276817
 *
 * @author zhun.huang 2018-11-12
 */
public class Test {

    public static void main(String[] args) {
        long[] counts = new long[10];
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        for (int j = 0; j < 10; j++) {

            List<User> randomList = User.getRandomList(3000);

            stopwatch.reset();
            stopwatch.start();

            ArrayList<User> copy1 = new ArrayList<User>(randomList);
            new BubbleSort().sort(copy1);
            counts[0] = counts[0]+ stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("冒泡："+stopwatch.elapsed(TimeUnit.MICROSECONDS));
            stopwatch.reset();
            stopwatch.start();

            ArrayList<User> copy2 = new ArrayList<User>(randomList);
            new InsertSort().sort(copy2);
            counts[1] = counts[1]+ stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("插入："+stopwatch.elapsed(TimeUnit.MICROSECONDS));
            stopwatch.reset();
            stopwatch.start();

            ArrayList<User> copy5 = new ArrayList<User>(randomList);
            new SelectSort().sort(copy5);
            counts[2] = counts[2]+ stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("选择："+stopwatch.elapsed(TimeUnit.MICROSECONDS));
            stopwatch.reset();
            stopwatch.start();

            ArrayList<User> copy3 = new ArrayList<User>(randomList);
            new MergeSort().sort(copy3);
            counts[3] = counts[3]+ stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("归并："+stopwatch.elapsed(TimeUnit.MICROSECONDS));
            stopwatch.reset();
            stopwatch.start();

            ArrayList<User> copy4 = new ArrayList<User>(randomList);
            new QuickSort().sort(copy4);
            counts[4] = counts[4]+ stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("快速："+stopwatch.elapsed(TimeUnit.MICROSECONDS));
            stopwatch.reset();
            stopwatch.start();


            for (int i = 0; i < randomList.size(); i++) {
                if (copy1.get(i).compareTo(copy2.get(i)) != 0
                        || copy1.get(i).compareTo(copy3.get(i)) != 0
                        || copy1.get(i).compareTo(copy4.get(i)) != 0
                        || copy1.get(i).compareTo(copy5.get(i)) != 0) {
                    System.out.println("方法有误");
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(counts[i]);
        }

    }

    public static class User implements Comparable<User> {
        private int age;

        private int money;

        public User(int age, int money) {
            this.age = age;
            this.money = money;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int compareTo(User o) {
            return Integer.valueOf(this.getAge()).compareTo(o.getAge());
        }

        public static List<User> getRandomList(int size) {
            Random random = new Random();
            ArrayList<User> users = Lists.newArrayList();
            for (int i = 0; i < size; i++) {
                users.add(new User(random.nextInt(), random.nextInt()));
            }
            return users;
        }

        public static void printList(List<User> list) {
            for (User user : list) {
                System.out.println(user);
            }
        }

        @Override
        public String toString() {
            return "{\"User\":{"
                    + "\"age\":\"" + age + "\""
                    + ", \"money\":\"" + money + "\""
                    + "}}";
        }
    }
}
