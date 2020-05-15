package genericity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/14
 */
public class MapGenericityTest {

    public static void main(String[] args) {
        Map map = new HashMap<String, Object>();
        map.put("1",1);
        System.out.println(map.get("1"));
        test(map);
        Map<String, BigDecimal> map2 = new HashMap<String, BigDecimal>();
        test((Map) map2);
    }

    public static void test(Map<String, Object> map) {
        System.out.println(map);
    }

}
