package support;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * description:
 * create: 2019-05-15
 *
 * @author zhun.huang
 */
public class UserModel {

	private int age;

	@JsonSerialize(using = EnumToIntegerSerializer.class)
	@JsonDeserialize(using = IntegerToEnumDeserializer.class)
	private ResultEnum success;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ResultEnum getSuccess() {
		return success;
	}

	public void setSuccess(ResultEnum success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "{\"UserModel\":{"
				+ "\"age\":\"" + age + "\""
				+ ", \"success\":\"" + success + "\""
				+ "}}";
	}
}
