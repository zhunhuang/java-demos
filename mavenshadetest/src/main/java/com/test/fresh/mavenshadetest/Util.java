package com.test.fresh.mavenshadetest;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

/**
 * description:
 * create: 2019-06-05
 * 项目需要用到的两个依赖都用到了com.google.guava，由于guava的版本在16.0以后，不向后兼容，
 * 这就导致出现了一个比较尴尬的地方 ：一个需要guava版本<=16.0，一个需要guava版本>=20.0
 * 这个时候, 可以通过使用 maven-shade插件来解决.
 * https://my.oschina.net/jeakiry/blog/2964169
 * @author zhun.huang
 */
public class Util {

	public static boolean isEmpty(String var) {
		return Strings.isNullOrEmpty(var);
	}

	public static<E> ImmutableList<E> toImmutableList(Iterable<E> iterable){
		return ImmutableList.copyOf(iterable);
	}
}
