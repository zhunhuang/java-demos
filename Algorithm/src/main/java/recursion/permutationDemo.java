package recursion;

import java.util.*;

/**
 * description:
 *
 *
 *
 * 把一个数组里面的数，全部用排列组合表示出来。
 * 比如 1，2 就可以表示成： 1，2，12，21
 * 需要考虑重复的情况。
 * @author zhun.huang 2019-08-01
 */
public class permutationDemo {

    /**
     * 解题思路： 先不考虑重复的情况。
     * 然后通过set去重即可。
     */

    public static Set<String> result = new HashSet<>();

    public static void main(String[] args) {
        String[] arr = new String[] {"1","2","12","21"};
        listAll(Arrays.asList(arr),"");

        System.out.println(result);
        System.out.println(result.size());
    }

    public static void listAll(List<String> candidate, String prefix) {
        if (prefix!=null && !prefix.equals("")) {
            result.add(prefix);
        }

        // 循环，就是选定该层中，遍历的顺序。
        // 每进入一层，list就需要把前面层数中已经遍历的移除。同时，需要将前缀作为中间结果，传入下一层
        for (int i = 0; i < candidate.size(); i++) {
            LinkedList<String> tmp = new LinkedList<>(candidate);
            listAll(tmp,prefix+ tmp.remove(i));
        }
    }
}
