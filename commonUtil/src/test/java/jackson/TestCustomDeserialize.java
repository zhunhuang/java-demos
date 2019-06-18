package jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import http.JacksonUtil;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * description:
 * create: 2019-06-18
 *
 * @author zhun.huang
 */
public class TestCustomDeserialize {

	@Test
	public void testNullToEmptyString() {
		final ObjectMapper instance = JacksonUtil.getInstance();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(String.class, new MyStringDeserializer());
		instance.registerModule(simpleModule);

		try {
			final String readValue = instance.readValue("null", String.class);
			assertEquals("", readValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNullToEmptyStringByAnnotation() {
		Man man = new Man();
		final String serialize = JacksonUtil.serialize(man);
		final Man newMan = JacksonUtil.deSerialize(serialize, Man.class);
		assertNotNull(newMan);
		assertEquals(null, man.name);

		assertEquals("", newMan.name);
	}

	public static class Man {
		@JsonDeserialize(using = MyStringDeserializer.class)
		public String name;

		public int age;
	}

	public static class MyStringDeserializer extends StringDeserializer {
		private static final long serialVersionUID = 7219771939461124884L;

		@Override
		public String getNullValue(DeserializationContext ctxt) throws JsonMappingException {
			System.out.println("null转空字符串");
			return "";
		}
	}
}
