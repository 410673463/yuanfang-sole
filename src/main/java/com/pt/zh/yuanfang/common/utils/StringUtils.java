package com.pt.zh.yuanfang.common.utils;

/**
 * 字符串工具类
 *
 * @date Sep 1, 2018
 */
public class StringUtils {

	/**
	 * 判空操作
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
	}

}
