package com.test.nolan;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * description:
 * create: 2019-07-12
 *
 * @author zhun.huang
 */
public class Generic {

	public static void main(String[] args) {
		IntResult intResult = new IntResult();
		final Type type = intResult.getClass().getGenericInterfaces()[0];
		ParameterizedType pt = (ParameterizedType) type;
		Type[] ts = pt.getActualTypeArguments();//这样就获取了ArrayList<ItemVo>中的泛型
		for (int i = 0; i < ts.length; ++i) {
			System.out.println(i + " 父类中的泛型为：" + ts[i]);
			Class<?> c = (Class<?>) ts[i];//如果需要使用这个类型 进行强转即可
			System.out.println(i + " 强转后类型为：" + c);
		}
		final Request<String> stringRequest = new Request<>();
	}

	public static interface Result<T> {

		T getResult();
	}

	public static class IntResult implements Result<Integer> {

		@Override
		public Integer getResult() {
			return 100;
		}
	}

	public static class Request<T> {

		T getRequest(T t) {
			return t;
		}
	}
}
