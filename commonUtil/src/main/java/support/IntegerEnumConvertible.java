package support;

/**
 * description:
 * create: 2019-05-15
 * 支持Integer 和 enum 序列化和反序列化 接口
 * @author zhun.huang
 */
public interface IntegerEnumConvertible {

	/**
	 * @return 返回对应的Integer value
	 */
	Integer getValue();

	/**
	 * @return 返回对应的描述信息
	 */
	String getMsg();
}