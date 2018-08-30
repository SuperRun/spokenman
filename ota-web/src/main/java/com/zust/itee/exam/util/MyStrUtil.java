package com.zust.itee.exam.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dxb
 *
 * @date Aug 5, 2016
 *
 */
public class MyStrUtil {

	/**
	 * 判断字符串是否为空
	 *
	 * @param strs
	 *            参数数量不限，用,相隔
	 * @return 为空返回真
	 */
	public static boolean hasEmpty(String... strs) {
		if (strs.length <= 0) {
			return true;
		}
		for (String str : strs) {
			if (str == null || "".equals(str.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是不是纯数字组成的字符串，
	 *
	 * @param strs
	 *            参数数量不限，用,相隔
	 * @return 如果所有传入参数都是对的
	 */
	public static boolean isDigitStr(String... strs) {
		if (strs.length <= 0) {
			return false;
		}
		for (String str : strs) {
			for (int j = 0; j < str.length(); j++) {
				if (!Character.isDigit(str.charAt(j))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否由字母和数字组成
	 *
	 * @why 用于判断注册用户名是否合法等
	 *
	 * @param strs
	 *            参数数量不限，用,相隔
	 * @return 传入参数中不包含非英文数字组成的字符串
	 */
	public static boolean isDigitAndLetter(String... strs) {

		if (strs.length <= 0) {
			return false;
		}
		Pattern pt = Pattern.compile("^[0-9a-zA-Z_]+$");
		for (String str : strs) {
			Matcher mt = pt.matcher(str);
			if (!mt.matches()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否仅包含英文字母、数字和汉字
	 *
	 * @why 判断用户姓名是否合法等
	 *
	 * @param str
	 * @return
	 */
	public static boolean isLetterDigitOrChinese(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		return str.matches(regex);
	}

}
