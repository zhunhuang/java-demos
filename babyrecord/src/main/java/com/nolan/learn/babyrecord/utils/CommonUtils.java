package com.nolan.learn.babyrecord.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * description:
 *
 * @author zhun.huang 2019-06-08
 */
public class CommonUtils {

    public static Splitter COMMA_SPLITTER = Splitter.on(",").trimResults();

    public static Joiner COMMA_JOINER = Joiner.on(",").skipNulls();
}
