import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * create: 2019-06-06
 *
 * @author zhun.huang
 */
public class Test4 {

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(27536520);
		List<String> list1 = new ArrayList<>();
		List list2 = new ArrayList<>();
		list1.add("1");// 不能 add(1)
		list2.add(1);

		list1.add("2");
		list2.add("2");

		List<String> list3 = list2;

		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
			System.out.println(list2.get(i));
			System.out.println(list3.get(i));
		}

	}
}
