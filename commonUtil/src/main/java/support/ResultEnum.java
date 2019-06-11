package support;

/**
 * description:
 * create: 2019-05-15
 *
 * @author zhun.huang
 */
public enum ResultEnum implements IntegerEnumConvertible {

	SUCESS(0, "成功"),
	FAIL(-1, "失败");

	private int code;
	private String msg;

	ResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getValue() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
