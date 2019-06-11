package lambdaEL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 * create: 2018-09-02
 * 字符串列表转成int列表, 并按从小到大排序
 * @author zhun.huang
 */
public class Demo1 {

	public static void main(String[] args) {
		List<String> unsortedStr = new ArrayList<String>();
		unsortedStr.add("2");
		unsortedStr.add("3");
		unsortedStr.add("1");
		final List<Integer> sortedStr = unsortedStr
				.stream()
				.map(Integer::valueOf)
				.sorted()
				.collect(Collectors.toList());
		System.out.println(sortedStr);
	}

}
