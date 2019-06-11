package com.test.tdd;

import java.security.InvalidParameterException;

/**
 * description:
 * create: 2019-05-05
 *
 * @author zhun.huang
 */
public class Calculator {

	public static int add(String var1, String var2) throws InvalidParameterException {
		int a = Integer.valueOf(var1);
		int b = Integer.valueOf(var2);
		return a+b;
	}
}
