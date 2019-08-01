package collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description:
 *
 * @author zhun.huang 2019-08-01
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {

        Map<String,String> lruMap = new LRUMap<>();

        lruMap.put("a","a");
        lruMap.put("b","b");
        lruMap.put("c","c");
        for (Map.Entry<String, String> stringStringEntry : lruMap.entrySet()) {
            System.out.println(stringStringEntry.getKey() + stringStringEntry.getValue());
        }

        lruMap.put("d","a");
        System.out.println("=======================");
        for (Map.Entry<String, String> stringStringEntry : lruMap.entrySet()) {
            System.out.println(stringStringEntry.getKey() + stringStringEntry.getValue());
        }

    }

    public static class LRUMap<K,V> extends LinkedHashMap<K,V> {

        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
            return size()>3;
        }
    }
}
