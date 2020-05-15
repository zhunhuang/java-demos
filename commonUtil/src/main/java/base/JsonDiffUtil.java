package base;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.*;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

/**
 * description:
 * create: 2019-06-26
 * JSON串0:
 * {
 * "name":"huang",
 * age:12,
 * loves:["money","play"]
 * }
 * JSON串1: {"name":"huang", age:12, loves:["money","play"]}
 * JSON串2: { age:12, "name":"huang",loves:["money","play"]}
 * JSON串3: {"name":"huang", age:12, loves:["play","money"]}
 * <p>
 * json 的diff, 一般可能有这几种比较的模式:
 * 0. 完全一致的比较, 上述4个串都不相等. 这种不常见, 直接比较字符串是否相等即可.
 * 1. 最严格的模式, 字段顺序必须一致. list的顺序也必须一致. 也就是说: 串1!=串2, 串2!=串3, 串3!=串1.但是: 串0==串1
 * 这种比较好比较
 * 2.
 *
 * @author zhun.huang
 */
public class JsonDiffUtil {

    static String json0 = "{\n" +
            "        \"name\":\"huang\",\n" +
            "        age:12,\n" +
            "        loves:[\"money\",\"play\"]\n" +
            "    }";
    static String json1 = "{\"name\":\"huang\", age:12, loves:[\"money\",\"play\"]}";
    static String json2 = "{ age:12, \"name\":\"huang\",loves:[\"money\",\"play\"]}";
    static String json3 = "{\"name\":\"huang\", age:12, loves:[\"play\",\"money\"]}";
    static String json4 = "{\"name\":\"huang\", age:12, loves:[\"money\",\"play\"],\"external\":\"多的属性\"}";

    public static void main(String[] args) throws JSONException {
        testStringEqual();
        testStrictMode();
        testStrictModeByJSON();
        testNON_EXTENSIBLEMode();
        testSTRICT_ORDERMode();
        testLENIENTMode();
    }

    public static void testStringEqual() {
        System.out.println("完全相等测试, 不允许格式不一致, 不允许顺序不一致, 不允许list不一致, 不允许多属性");
        boolean equals = StringUtils.equals(json0, json1);
        System.out.println(equals);

        equals = StringUtils.equals(json0, json2);
        System.out.println(equals);

        equals = StringUtils.equals(json0, json3);
        System.out.println(equals);

        equals = StringUtils.equals(json0, json4);
        System.out.println(equals);

        equals = StringUtils.equals(json2, json3);
        System.out.println(equals);
    }

    public static void testStrictModeByJSON() {
        System.out.println("严格模式比较, 允许格式不一致, 不允许顺序不一致, 不允许list不一致, 不允许多属性");

        try {
            JSONObject jsonObject0 = new JSONObject(json0);
            JSONObject jsonObject1 = new JSONObject(json1);
            JSONObject jsonObject2 = new JSONObject(json2);
            JSONObject jsonObject3 = new JSONObject(json3);
            JSONObject jsonObject4 = new JSONObject(json4);

            System.out.println(jsonObject0.toString().equals(jsonObject1.toString()));
            System.out.println(jsonObject0.toString().equals(jsonObject2.toString()));
            System.out.println(jsonObject0.toString().equals(jsonObject3.toString()));
            System.out.println(jsonObject0.toString().equals(jsonObject4.toString()));
            System.out.println(jsonObject2.toString().equals(jsonObject3.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void testStrictMode() throws JSONException {
        System.out.println("严格模式比较, 允许格式不一致, 顺序不一致, 但不允许list不一致, 不允许多属性");

        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(json0, json1, JSONCompareMode.STRICT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json2, JSONCompareMode.STRICT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json3, JSONCompareMode.STRICT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json4, JSONCompareMode.STRICT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json2, json3, JSONCompareMode.STRICT);
        System.out.println(jsonCompareResult.passed());

    }

    /**
     *
     */
    public static void testSTRICT_ORDERMode() throws JSONException {
        System.out.println("STRICT_ORDERModeE模式比较, 允许格式不一致, 顺序不一致, 多余的属性, 但不允许list顺序不一致");

        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(json0, json1, JSONCompareMode.STRICT_ORDER);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json2, JSONCompareMode.STRICT_ORDER);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json3, JSONCompareMode.STRICT_ORDER);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json4, JSONCompareMode.STRICT_ORDER);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json2, json3, JSONCompareMode.STRICT_ORDER);
        System.out.println(jsonCompareResult.passed());
    }

    /**
     *
     */
    public static void testNON_EXTENSIBLEMode() throws JSONException {
        System.out.println("NON_EXTENSIBLE模式比较, 允许格式不一致, 顺序不一致, list顺序不一致, 但不允许多属性");

        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(json0, json1, JSONCompareMode.NON_EXTENSIBLE);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json2, JSONCompareMode.NON_EXTENSIBLE);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json3, JSONCompareMode.NON_EXTENSIBLE);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json4, JSONCompareMode.NON_EXTENSIBLE);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json2, json3, JSONCompareMode.NON_EXTENSIBLE);
        System.out.println(jsonCompareResult.passed());
    }

    /**
     *
     */
    public static void testLENIENTMode() throws JSONException {
        System.out.println("最宽松的LENIENT模式比较, 允许格式不一致, 顺序不一致, list顺序不一致, 允许多属性");

        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(json0, json1, JSONCompareMode.LENIENT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json2, JSONCompareMode.LENIENT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json3, JSONCompareMode.LENIENT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json0, json4, JSONCompareMode.LENIENT);
        System.out.println(jsonCompareResult.passed());

        jsonCompareResult = JSONCompare.compareJSON(json2, json3, JSONCompareMode.LENIENT);
        System.out.println(jsonCompareResult.passed());

    }

    @Test
    public void testIgnore() throws JSONException {
		 String json1 = "{\"name\":\"huang\", age:12, loves:[\"money\",\"play\"],\"ruleJson\":\"afaffasf\"}";

		 String json2 = "{ age:12, \"name\":\"huang1\",loves:[\"money\",\"play\"],\"ruleJson\":\"afaffasf\"}}";

        Customization customization = new Customization("name", new AlwaysTrueMatcher<>());
        Customization customization2 = new Customization("ruleJson", new JSONMatcher<>());
        JSONComparator jsonComparator = new CustomComparator(JSONCompareMode.LENIENT, customization,customization2);

        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(json1, json2, jsonComparator);

        JSONCompareResult jsonCompareResult2 = JSONCompare.compareJSON(json1, json2, JSONCompareMode.STRICT);
        System.out.println( "第一种对比是否通过：" + jsonCompareResult.passed());
        System.out.println("第2种对比结果：" +jsonCompareResult2);
    }

    public static class AlwaysTrueMatcher<T> implements ValueMatcher<T> {

        @Override
        public boolean equal(T o1, T o2) {
            return true;
        }
    }

    public static class JSONMatcher<T> implements ValueMatcher<T> {

        @Override
        public boolean equal(T o1, T o2) {
            try {
                return  JSONCompare.compareJSON((String) o1, (String)o2,JSONCompareMode.LENIENT).passed();
            } catch (JSONException e) {
                return false;
            }
        }
    }
}
