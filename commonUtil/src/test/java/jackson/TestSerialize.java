package jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.JacksonUtil;
import jackson.model.ResponseModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * description:
 * create: 2019-06-18
 * 不管jackson的序列化内部如何处理的, API使用还是很简单的.
 * @author zhun.huang
 */
public class TestSerialize {

	@Test()
	public void testNull(){
		final String serialize = JacksonUtil.serialize(null);
		assertEquals("null", serialize);
	}

	@Test
	public void testNullString(){
		final String serialize = JacksonUtil.serialize("null");
		assertEquals("\"null\"", serialize);
	}

	@Test
	public void baseTest(){
		ResponseModel responseModel = new ResponseModel();
		final String serialize = JacksonUtil.serialize(responseModel);
		assertEquals("{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":null}", serialize);
	}

	@Test
	public void testList(){
		List<String> list = new ArrayList<>();
		list.add("num1");
		list.add("num2");
		list.add("num3");
		final String serialize = JacksonUtil.serialize(list);
		assertEquals("[\"num1\",\"num2\",\"num3\"]", serialize);
	}

	@Test
	public void testMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("num1","num1");
		map.put("num2",100);
		map.put("num3",true);
		final String serialize = JacksonUtil.serialize(map);
		assertEquals("{\"num1\":\"num1\",\"num3\":true,\"num2\":100}", serialize);
		System.out.println(serialize);
	}

	@Test
	public void testGeneric() {
		ResponseModel<Boolean> responseModel = new ResponseModel<>();
		responseModel.setData(true);
		final String serialize = JacksonUtil.serialize(responseModel);
		assertEquals("{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":true}", serialize);
		System.out.println(serialize);
	}

		@Test
	public void testGenericComplex(){
		ResponseModel<List<Map<String, Object>>> responseModel= new ResponseModel<>();
		List<Map<String, Object>> data = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("num1","num1");
		map.put("num2",100);
		map.put("num3",true);
		data.add(map);
		data.add(map);
		responseModel.setData(data);

		final String serialize = JacksonUtil.serialize(responseModel);
		assertEquals("{\"success\":false,\"errorCode\":0,\"errorMsg\":null,\"data\":[{\"num1\":\"num1\",\"num3\":true,\"num2\":100},{\"num1\":\"num1\",\"num3\":true,\"num2\":100}]}", serialize);

		System.out.println(serialize);
	}
}
