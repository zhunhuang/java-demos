package support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * description:
 * create: 2019-05-15
 * jackson框架 自定义枚举反序列化
 * 注解使用方法： @JsonDeserialize(using = IntegerToEnumDeserializer.class)
 * 注解可用位置： {@link com.fasterxml.jackson.databind.annotation.JsonSerialize}
 *
 * @author zhun.huang
 */
public class IntegerToEnumDeserializer extends JsonDeserializer<IntegerEnumConvertible> {

	@Override
	public IntegerEnumConvertible deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		String currentName = jp.getCurrentName();
		Object currentValue = jp.getCurrentValue();
		@SuppressWarnings("rawtypes")
		Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());

		JsonFormat annotation = (JsonFormat) findPropertyType.getAnnotation(JsonFormat.class);

		IntegerEnumConvertible valueOf;
		if (annotation == null || annotation.shape() != JsonFormat.Shape.OBJECT) {
			valueOf = fromCode(node.asInt(), findPropertyType);
		} else {
			valueOf = fromCode(node.get("code").asInt(), findPropertyType);
		}
		return valueOf;
	}

	public static <E extends IntegerEnumConvertible> E fromCode(Integer code, Class<E> enumType) {
		for (E anEnum : enumType.getEnumConstants()) {
			if (anEnum.getValue().equals(code)) {
				return anEnum;
			}
		}
		return null;
	}
}
