package base;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.LRUMap;

import java.util.Collections;
import java.util.Map;

/**
 * description:
 * create: 2019-06-26
 * 固定大小, 基于LRU算法淘汰的map.
 * @author zhun.huang
 */
public class FixedSizeMapUtil {

	public static Map<String, String> fixedSizeMap = new LRUMap(100);
	public static Map<String, String> fixedSizeSafeMap = MapUtils.synchronizedMap(new LRUMap(100));

	public static void main(String[] args) {
		int size = 10;
		Map<String, String> scrollIdFixedSizeMaps = Collections.synchronizedMap(new LRUMap(size));

		for (int i = 0; i < size; i++) {
			scrollIdFixedSizeMaps.put(String.valueOf("a" + i), String.valueOf("v" + i));
		}

		for (int i = 0; i < size; i++) {
			scrollIdFixedSizeMaps.put(String.valueOf("b" + i), String.valueOf("v" + i));
			for (int j = 0; j < size; j++) {
				System.out.print("a" + j + ": " + scrollIdFixedSizeMaps.get("a" + j) + "\t");
			}
			// 保证顺序为写入顺序
			for (int j = 0; j <= i; j++) {
				scrollIdFixedSizeMaps.get("b" + j);
			}
			System.out.println(scrollIdFixedSizeMaps.size());
			System.out.println();
		}
	}
}
