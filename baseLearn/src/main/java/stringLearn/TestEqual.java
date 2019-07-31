package stringLearn;

import java.lang.reflect.Field;

/**
 * @author: zhun.huang
 * @create: 2018-03-16 下午6:12
 * @email: nolan.zhun@gmail.com
 * @description:
 *  String 的equal, 总结就是:一个不要
 *  不要使用==号,比较的是尽管有些时候会相等.
 */
public class TestEqual {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        String s1 = new String("1");
        // 常量池生成了一个string对象， 其内部的char数组和s1引用的string对象内部的char数组是同一个数组。
        s1.intern();
        System.out.println(s1 == s1.intern());//false
        System.out.println(s1 == "1");//false
        // 都是常量池里面的那个string对象
        System.out.println(s1.intern() == "1");//true

        //  会调用StringBuilder的append方法，和toString方法生成一个常量。
        String s3 = new String("1") + new String("1");
        String s5 = new String("1") + new String("1");
        // 可以理解为s3指向常量池
        System.out.println(s3 == s3.intern());//true
        System.out.println();
        System.out.println(s5 == s5.intern());//false
        System.out.println(s5.intern() == s3.intern());//true



        String s4 = "11";
        String s8 = new String("11");
        System.out.println("s8=s4?" + (s8==s4));
        System.out.println(s3 == s4);//true
        System.out.println(s4 == s5);//false
        // 常量池已经存在, s5虽然是个新的string对象，但内部的char数组仍然和s3的是一样的。
        Field valueField = s3.getClass().getDeclaredField("value");
        valueField.setAccessible(true);
        char[] o3 = (char[])valueField.get(s3);
        char[] o4 = (char[])valueField.get(s4);
        char[] o5 = (char[])valueField.get(s5);
        char[] o8 = (char[])valueField.get(s8);
        System.out.println(o3[0] + o3[1]);
        System.out.println(o4[0]+ o4[1]);
        System.out.println(o5[0]+ o5[1]);
        System.out.println(o5[0]+ o5[1]);
        System.out.println( "s3.char[] == s4.char[]? " + (o3==o4));
        System.out.println( "s3.char[] == s5.char[]? " + (o3==o5));
        System.out.println( "s3.char[] == s8.char[]? " + (o3==o8));

        String s6 = add("1");
        String s7 = add("1");
        System.out.println(s6 == s6.intern());//true
        // 常量池已经存在, s5不改变?
        System.out.println(s7 == s7.intern());//false
    }

    public static String add(String s1) {
        return new String(s1) + "a";
    }
}
