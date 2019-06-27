package base;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;

/**
 * description:
 * create: 2019-06-24
 *
 * @author zhun.huang
 */
public class MapDiffUtil {

	public static void main(String[] args) {
		String note =  "guava 的maps工具类可以diff出来两个map的: entity only left, entity only right, entity difference";
		System.out.println(note);
		diffFixed();
		diffRandom();
		diffSort();
		diffSort2();
	}

	public static void diffRandom() {
		System.out.println("==================diffRandom的diff:");
		final Family family1 = getRandomFamily();
		final Family family2 = getRandomFamily();
		System.out.println("family1: " + family1);
		System.out.println("family2: " + family2);

		final Map map1 = JacksonUtil.convertValue(family1, Map.class);
		final Map map2 = JacksonUtil.convertValue(family2, Map.class);
		final MapDifference difference = Maps.difference(map1, map2);
		System.out.println("difference: " + difference);
	}

	public static void diffFixed() {
		System.out.println("==================diffFixed的diff:");
		final Family family1 = getFixedFamily();
		final Family family2 = getFixedFamily();
		System.out.println("family1: " + family1);
		System.out.println("family2: " + family2);
		final Map map1 = JacksonUtil.convertValue(family1, Map.class);
		final Map map2 = JacksonUtil.convertValue(family2, Map.class);
		final MapDifference difference = Maps.difference(map1, map2);
		System.out.println("difference: " + difference);
	}

	/**
	 * 如果关心顺序的话
	 */
	public static void diffSort(){
		System.out.println("==================diffSort的diff:");
		User user1 = new User("user1", 1);
		User user2 = new User("user2", 1);
		User user3 = new User("user3", 1);
		Family family1 = new Family("family",Lists.newArrayList(user1,user2,user3));
		Family family2 = new Family("family",Lists.newArrayList(user2,user1,user3));
		final Map map1 = JacksonUtil.convertValue(family1, Map.class);
		final Map map2 = JacksonUtil.convertValue(family2, Map.class);
		final MapDifference difference = Maps.difference(map1, map2);
		System.out.println(difference.areEqual());
	}

	/**
	 * 如果不关心顺序的话
	 */
	public static void diffSort2(){
		System.out.println("==================diffSort2的diff:");
		User user1 = new User("user1", 1);
		User user2 = new User("user2", 1);
		User user3 = new User("user3", 1);
		Family family1 = new Family("family",Lists.newArrayList(user1,user2,user3));
		Family family2 = new Family("family",Lists.newArrayList(user2,user1,user3));
		final Map map1 = JacksonUtil.convertValue(family1, SortedMap.class);
		final Map map2 = JacksonUtil.convertValue(family2, SortedMap.class);
		final MapDifference difference = Maps.difference(map1, map2);
		System.out.println(difference.areEqual());
	}

	public static Family getRandomFamily() {
		final Random random = new Random();
		User user1 = new User("user" + random.nextInt(), random.nextInt());
		User user2 = new User("user" + random.nextInt(), random.nextInt());
		User user3 = new User("user" + random.nextInt(), random.nextInt());

		return new Family("family" + random.nextInt(), Lists.newArrayList(user1, user2, user3));

	}

	public static Family getFixedFamily() {
		User user1 = new User("user1", 1);
		User user2 = new User("user2", 1);
		User user3 = new User("user3", 1);
		return new Family("family", Lists.newArrayList(user1, user2, user3));
	}

	public static class User {
		private String name;

		private int age;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
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

		@Override
		public String toString() {
			return "{\"User\":{"
					+ "\"name\":\"" + name + "\""
					+ ", \"age\":\"" + age + "\""
					+ "}}";
		}
	}

	public static class Family {
		private String familyName;

		private List<User> users;

		public Family(String familyName, List<User> users) {
			this.familyName = familyName;
			this.users = users;
		}

		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		@Override
		public String toString() {
			return "{\"Family\":{"
					+ "\"familyName\":\"" + familyName + "\""
					+ ", \"users\":" + users
					+ "}}";
		}
	}
}
