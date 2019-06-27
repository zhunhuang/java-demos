package jackson;

import base.JacksonUtil;
import jackson.model.Person;
import jackson.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * create: 2019-06-26
 *
 * @author zhun.huang
 */
public class TestObjectConvert {

	@Test
	public void testSuperToSubClass() {
		Person person = new Person();
		person.setAge(10);
		person.setName("nolan");

		final Student student = JacksonUtil.convertValue(person, Student.class);
		student.setDegree(6);
		// 深拷贝
		System.out.println(JacksonUtil.serialize(student));
		person.setName("Huang");
		System.out.println(JacksonUtil.serialize(student));

		Assert.assertEquals(false, person == student);
	}

	@Test
	public void testSubToSuper() {
		Student student = new Student();
		student.setDegree(6);
		student.setAge(10);
		student.setName("nolan");

		final Person person = JacksonUtil.convertValue(student, Person.class);
		// 浅拷贝
		System.out.println(JacksonUtil.serialize(person));
		student.setName("Huang");
		System.out.println(JacksonUtil.serialize(person));

		Assert.assertEquals(true, student == person);
	}

	@Test
	public void ObjectToMap(){
		Student student = new Student();
		student.setDegree(6);
		student.setAge(10);
		student.setName("nolan");

		final Map map = JacksonUtil.convertValue(student, Map.class);
		System.out.println(map);

		Assert.assertEquals(LinkedHashMap.class, map.getClass());
		Assert.assertEquals("nolan",map.get("name"));
	}

	@Test
	public void ListToMap(){
		System.out.println("list 没办法转map, 老版本是直接抛异常, 新版本是直接返回null");
		List<String> list = new ArrayList<>();
		list.add("123");
		list.add("456");

		final Map map = JacksonUtil.convertValue(list, Map.class);
		System.out.println(map);
		list.remove("123");
		list.remove("456");

		final Map map2 = JacksonUtil.convertValue(list, Map.class);
		System.out.println(map2);

		final Map map3 = JacksonUtil.convertValue(new ArrayList(), Map.class);
		System.out.println(map3);

	}
}
