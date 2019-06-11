package support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * description: 用于springMVC中Integer->枚举的自定义转换
 * create: 2019-05-15
 * 自定义converter 全局生效
 * <p>
 * <mvc:annotation-driven conversion-service="conversionService"/>
 * <p>
 * <bean class="org.springframework.context.support.ConversionServiceFactoryBean" id="conversionService">
 * <property name="converters">
 * <list>
 * <bean class="com.test.fuwu.support.IntegerToEnumTypeConverterFactory"/>
 * </list>
 * </property>
 * </bean>
 *
 * @author zhun.huang
 */
public class IntegerToEnumTypeConverterFactory implements ConverterFactory<String, IntegerEnumConvertible> {

	@Override
	public <T extends IntegerEnumConvertible> Converter<String, T> getConverter(Class<T> targetType) {
		return new IntegerToEnumConverter<>(targetType);
	}

	public static class IntegerToEnumConverter<T extends IntegerEnumConvertible> implements Converter<String, T> {

		private final T[] values;

		public IntegerToEnumConverter(Class<T> targetType) {
			values = targetType.getEnumConstants();
		}

		@Override
		public T convert(String source) {
			for (T t : values) {
				if (t.getValue().equals(Integer.valueOf(source))) {
					return t;
				}
			}
			return null;
		}
	}
}
