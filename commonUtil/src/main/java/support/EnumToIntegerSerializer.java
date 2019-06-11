package support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * description:
 * create: 2019-05-15
 * jackson框架 自定义枚举序列化
 * 注解使用方法： @JsonSerialize(using = EnumToIntegerSerializer.class)
 * 注解可用位置： {@link com.fasterxml.jackson.databind.annotation.JsonSerialize}
 *
 * @author zhun.huang
 */
public class EnumToIntegerSerializer<T extends IntegerEnumConvertible> extends JsonSerializer<T> {

	@Override
	public void serialize(T convertibleEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (convertibleEnum == null) {
			jsonGenerator.writeNull();
		} else {
			jsonGenerator.writeNumber(convertibleEnum.getValue());
		}
	}
}
