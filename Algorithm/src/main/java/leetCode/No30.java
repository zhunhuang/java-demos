package leetCode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * description:
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhun.huang 2019-08-02
 */
public class No30 {


    public static List<Integer> findSubstring(String s, String[] words) {
        if (words.length==0 || "".equals(s)) {
            return new ArrayList<>();
        }
        findAllPossible("", Arrays.asList(words));
        List<String> list = new ArrayList<>(allPossible);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (s.substring(i).startsWith(list.get(j))) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    public static Set<String> allPossible = new HashSet<>();
    public static void findAllPossible(String result, List<String> words) {
        if (words.size()==0) {
            allPossible.add(result);
            return;
        }
        for (int i = 0; i < words.size(); i++) {
            List<String> tmp = new ArrayList<>(words);
            String remove = tmp.remove(i);
            findAllPossible(result+remove, tmp);
        }
    }

    public static void main(String[] args) {
        List<String> toTest = Lists.newArrayList("for","bar","the");
        findAllPossible("",toTest);
        System.out.println(findSubstring("barfoofoobarthefoobarman",new String[]{"foo","bar","the"}));
        System.out.println(allPossible);
    }
}
