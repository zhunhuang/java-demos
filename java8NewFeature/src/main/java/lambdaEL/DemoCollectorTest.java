package lambdaEL;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * description:
 * create: 2019-05-05
 *
 * @author zhun.huang
 */
public class DemoCollectorTest {

	/**
	 * 1. 根据model的某一个字段分组
	 */
	@Test
	public void testGroupBy() {
		User user1 = new User("ming", 14, false);
		User user2 = new User("guo", 26, true);
		User user3 = new User("zhun", 24, true);
		User user4 = new User("gma", 64, false);
		User user5 = new User("anc", 44, true);

		List<User> users = Lists.newArrayList(user1, user2, user3, user4, user5);

		final Map<String, List<User>> groupedUsers = users
				.stream()
				.collect(Collectors.groupingBy(user -> (user.age / 10 * 10) + "年龄段"));

		System.out.println(groupedUsers);
	}

	/**
	 * 2. 根据model的某一个字段排序
	 */
	@Test
	public void testSort() {
		User user1 = new User("ming", 14, false);
		User user2 = new User("guo", 26, true);
		User user3 = new User("zhun", 24, true);
		User user4 = new User("gma", 64, false);
		User user5 = new User("anc", 44, true);
		List<User> users = Lists.newArrayList(user1, user2, user3, user4, user5);

		final List<User> sortedByAgeUsers = users.stream()
				.sorted(Comparator.comparingInt(o -> o.age))
				.collect(Collectors.toList());

		System.out.println(sortedByAgeUsers);
	}

	/**
	 * 3. 根据model的某一个属性去重.
	 */
	@Test
	public void testDistinct() {
		User user1 = new User("ming", 14, false);
		User user2 = new User("guo", 26, true);
		User user3 = new User("zhun", 24, true);
		User user4 = new User("gma", 64, false);
		User user5 = new User("anc", 44, true);
		User user6 = new User("anc", 54, true);
		User user7 = new User("anc", 64, true);
		List<User> users = Lists.newArrayList(user1, user2, user3, user4, user5, user6, user7);

		final List<User> distinctByNameUsers = users.stream()
				.filter(distinctByKey(User::getName))
				.collect(Collectors.toList());
		System.out.println(distinctByNameUsers);
	}



























	/**
	 * 定义一个过滤器进行去重
	 * 不涉及到共享变量，没有线程安全问题
	 * 为什么是这样写的，因为上面的filter是需要一个Predicate返回的参数的
	 * 用concurrentHashMap里面的putIfAbsent进行排重
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
	}


	public static class User {

		private String name;

		private int age;

		private boolean male;

		public User(String name, int age, boolean male) {
			this.name = name;
			this.age = age;
			this.male = male;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public boolean isMale() {
			return male;
		}

		public void setMale(boolean male) {
			this.male = male;
		}

		@Override
		public String toString() {
			return "{\"User\":{"
					+ "\"name\":\"" + name + "\""
					+ ", \"age\":\"" + age + "\""
					+ ", \"male\":\"" + male + "\""
					+ "}}";
		}
	}

}
