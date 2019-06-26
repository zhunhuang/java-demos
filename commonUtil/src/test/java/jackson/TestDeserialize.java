package jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import base.JacksonUtil;
import jackson.model.ResponseModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * description:
 * create: 2019-06-18
 *
 * @author zhun.huang
 */
public class TestDeserialize {

	@Test()
	public void testNull(){
		final String serialize = "null";
		final String s = JacksonUtil.deSerialize(serialize, String.class);
		final Object o = JacksonUtil.deSerialize(serialize, Object.class);
		final ResponseModel responseModel = JacksonUtil.deSerialize(serialize, ResponseModel.class);

		assertEquals("null",serialize);
		assertEquals(null,s);
		assertEquals(null,o);
		assertEquals(null,responseModel);
	}

	@Test
	public void testList(){
		String list = "[1,2,3,4,5,\"abc\"]";
		final List list1 = JacksonUtil.deSerialize(list, List.class);
		assertNotNull(list1);
		assertEquals(ArrayList.class, list1.getClass());
		assertEquals(1,list1.get(0));
		assertEquals("abc",list1.get(5));
		System.out.println(list1);
	}

	@Test
	public void testList2(){
		String list ="[{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":\"你好啊\"},{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":true}]";
		final List list1 = JacksonUtil.deSerialize(list, List.class);
		assertNotNull(list1);
		assertEquals(ArrayList.class, list1.getClass());
		assertEquals(LinkedHashMap.class,list1.get(0).getClass());
		assertEquals(false, ((LinkedHashMap)list1.get(0)).get("success"));
		assertEquals(0, ((LinkedHashMap)list1.get(0)).get("errorCode"));
		assertEquals("你好啊", ((LinkedHashMap)list1.get(0)).get("data"));
		System.out.println(list1);
	}

	@Test
	public void testList3(){
		String list = "[1,\"2\",3,4,5,\"abc\"]";
		final List<String> list1 = JacksonUtil.deSerializeList(list, String.class);
		assertNotNull(list1);
		assertEquals(ArrayList.class, list1.getClass());
		assertEquals("1",list1.get(0));
		assertEquals("2",list1.get(1));
		assertEquals("abc",list1.get(5));
		System.out.println(list1);
	}

	@Test
	public void testListObject(){
		String list = "[{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":\"你好啊\"},{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":null}]";
		final List<ResponseModel<String>> list1 = JacksonUtil.deSerialize(list, new TypeReference<List<ResponseModel<String>>>() {
		});
		assertNotNull(list1);
		assertEquals(ArrayList.class, list1.getClass());
		assertEquals(ResponseModel.class,list1.get(0).getClass());
		assertEquals("你好啊",list1.get(0).getData());
		assertEquals(null,list1.get(1).getData());
	}

}
