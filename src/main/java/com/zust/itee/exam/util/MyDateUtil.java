package com.zust.itee.exam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dxb
 *
 * @date Aug 5, 2016
 *
 */
public class MyDateUtil {

	private static final SimpleDateFormat mySimpleDateFormatAccurate = new SimpleDateFormat(
			"yyyy年M月d H:mm:ss");
	private static final SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(
			"yyyy年M月d日 H:mm");
	private static final SimpleDateFormat mySimpleDateFormatAjax = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");
	private static final SimpleDateFormat mySimpleDateFormatAjaxDateOnly = new SimpleDateFormat(
			"yyyy/MM/dd");
	private static final SimpleDateFormat mySimpleDateFormatSimple = new SimpleDateFormat(
			"M月d日 H:mm");
	private static final SimpleDateFormat mySimpleDateFormatDateOnlySimple = new SimpleDateFormat(
			"M月d日");
	private static final SimpleDateFormat mySimpleDateFormatDateOnly = new SimpleDateFormat(
			"yyyy年M月d日");
	private static final SimpleDateFormat mySimpleDateFormatToAjax = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");

	private static final SimpleDateFormat mySimpleDateFormatToAjaxDateOnly=new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 获取日期字符串，
	 *
	 * @param date
	 * @return 如果年份与今年相同，M月d日，反之 yyyy年M月d日
	 */
	public static String date2strMD(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dateYear = calendar.get(Calendar.YEAR);
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);

		if (dateYear == nowYear) {
			return mySimpleDateFormatDateOnlySimple.format(date);
		} else {
			return mySimpleDateFormatDateOnly.format(date);
		}
	}

	/**
	 * M月d日 H:mm
	 * @param date
	 * @return
	 */
	public static String date2strMDHM(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dateYear = calendar.get(Calendar.YEAR);
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);

		if (dateYear == nowYear) {
			return mySimpleDateFormatSimple.format(date);
		} else {
			return mySimpleDateFormat.format(date);
		}
	}

	/**
	 * 前端传过来的格式
	 *
	 * @param str
	 *            前端传来的字符串 yyyy/MM/dd HH:mm
	 * @return
	 */
	public static Date str2dateAjax(String str) {
		try {
			return mySimpleDateFormatAjax.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 前端传过来的格式
	 *
	 * @param str
	 *            前端传来的字符串 yyyy/MM/dd
	 * @return
	 */
	public static Date str2dateAjaxDateOnyl(String str) {
		try {
			return mySimpleDateFormatAjaxDateOnly.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @author sdy
	 *
	 * @why 计算系统时间时，出生年月为date的年龄
	 *
	 * @param
	 * @return
	 */
	public static int countAge(Date date) {
		/*
		 * 获取当前年月日
		 */
		Calendar now = Calendar.getInstance();
		int nowYear = now.get(Calendar.YEAR);
		int nowMonth = now.get(Calendar.MONTH);
		// 当前月的第几天
		int nowDay = now.get(Calendar.DAY_OF_MONTH);

		/*
		 * 获取出生年月日
		 */
		Calendar birth = Calendar.getInstance();
		birth.setTime(date);
		int birthYear = birth.get(Calendar.YEAR);
		int birthMonth = birth.get(Calendar.MONTH);
		int birthDay = birth.get(Calendar.DAY_OF_MONTH);

		int age = nowYear - birthYear - 1;
		if (nowMonth > birthMonth
				|| (nowMonth == birthMonth && nowDay >= birthDay)) {
			// 生日过了加一岁
			age += 1;
		}
		return age;

	}

	/**
	 *
	 * @author sdy
	 *
	 * @why	数据库获取的日期格式转化为前端js可以控制的格式,yyyy/MM/dd hh:mm
	 */
	public static String dateToAjaxString(Date date){
		try {
			if(date!=null){
				return mySimpleDateFormatToAjax.format(date);
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 数据库获取的日期格式转化为前端js可以控制的格式,yyyy/MM/dd
	 * @param date
	 * @return
	 */
	public static String dateToAjaxDateOnlyString(Date date){
		try {
			if(date!=null){
				return mySimpleDateFormatToAjaxDateOnly.format(date);
			}else{
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
