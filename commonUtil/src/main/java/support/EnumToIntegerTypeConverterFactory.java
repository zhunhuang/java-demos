package support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * description: 用于springMVC中枚举->number的自定义转换
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
public class EnumToIntegerTypeConverterFactory implements ConverterFactory<IntegerEnumConvertible, Integer> {

	@Override
	public <T extends Integer> Converter<IntegerEnumConvertible, T> getConverter(Class<T> aClass) {
		return new EnumToNumberConverter();
	}

	public static class EnumToNumberConverter<T extends Integer> implements Converter<IntegerEnumConvertible, Integer> {

		@Override
		public Integer convert(IntegerEnumConvertible tIntegerEnumConvertible) {
			return tIntegerEnumConvertible.getValue();
		}
	}
}
