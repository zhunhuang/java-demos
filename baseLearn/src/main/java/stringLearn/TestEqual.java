package stringLearn;

/**
 * @author: zhun.huang
 * @create: 2018-03-16 下午6:12
 * @email: nolan.zhun@gmail.com
 * @description:
 *  String 的equal, 总结就是:一个不要
 *  不要使用==号,比较的是尽管有些时候会相等.
 */
public class TestEqual {

    public static void main(String[] args) {

        String s1 = new String("1");
        s1.intern();
        System.out.println(s1 == s1.intern());//false
        System.out.println(s1 == "1");//false
        System.out.println(s1.intern() == "1");//true

        String s3 = new String("1") + new String("1");
        String s5 = new String("1") + new String("1");
        // 可以理解为s3指向常量池?
        System.out.println(s3 == s3.intern());//true
        // 常量池已经存在, s5不改变?
        System.out.println(s5 == s5.intern());//false
        System.out.println(s5.intern() == s3.intern());
        String s4 = "11";
        System.out.println(s3 == s4);//true
        System.out.println(s4 == s5);//false
    }
}
