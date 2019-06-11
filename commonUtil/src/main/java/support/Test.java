package support;

import http.JacksonUtil;

/**
 * description:
 * create: 2019-05-15
 *
 * @author zhun.huang
 */
public class Test {

	public static void main(String[] args) {
		UserModel t = new UserModel();
		t.setAge(12);
		t.setSuccess(ResultEnum.FAIL);
		String text = JacksonUtil.serialize(t);
//		枚举被序列化成了int值
		System.out.println(text);

		// 设置JsonDeserialize 后， 调用具体的反序列化类进行反序列化。
		UserModel t2 = JacksonUtil.deSerialize(text, UserModel.class);

		System.out.println(JacksonUtil.serialize(t2));
	}
}
