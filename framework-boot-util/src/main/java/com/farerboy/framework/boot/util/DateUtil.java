package com.farerboy.framework.boot.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具
 *
 * @author farerboy
 * @date 2020/12/29 8:44 下午
 */
public class DateUtil {
	public static final String C_DATE_DIVISION = "-";

	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyy$MM$dd = "yyyy/MM/dd";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyy_MM="yyyy-MM";
	public static final String HH_mm_ss = "HH:mm:ss";
	public static final String yyyyMMddHHmmss="yyyyMMddHHmmss";

	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final int C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

	/**
	 * @decription 获取系统当前日期时间
	 * Return the current date
	 * @parameter
	 * @return java.util.Date
	 * @throws
	 * @version 1.0.0
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return currDate;
	}

	/**
	 * @decription 获取系统当前日期时间字符串，默认格式 yyyy-MM-dd
	 * Return the current date string
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String getCurrentDateStr() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return formatDate(currDate);
	}

	/**
	 * @decription 获取系统当前日期时间字符串，默认格式 yyyy-MM-dd hh:mm:ss
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String getCurrentDateTimeStr(){
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return formatDateTime(currDate);
	}
	
	/**
	 * @decription 根据指定格式，获取系统当前日期时间字符串
	 * Return the current date in the specified format
	 * @parameter 日期格式，如 yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String getCurrentDateStr(String strFormat) {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return format(currDate, strFormat);
	}

	/**
	 * @decription 字符串格式时间转换为 Date 类型数据，只取计算日期
	 * Parse a string and return a date value
	 * @parameter yyyy-MM-dd 该格式类型的时间字符串
	 * @return
	 * @throws ParseException 
	 * @version 1.0.0
	 */
	public static Date parseDate(String dateValue) throws ParseException {
		return parseDate(yyyy_MM_dd, dateValue);
	}

	/**
	 * 
	 * @decription 字符串格式时间转换为 Date 类型数据
	 * Parse a strign and return a datetime value
	 * @parameter yyyy-MM-dd hh:mm:ss 该格式类型的时间字符串
	 * @return
	 * @throws ParseException 
	 * @throws
	 * @version 1.0.0
	 */
	public static Date parseDateTime(String dateValue) throws ParseException {
		return parseDate(yyyy_MM_dd_HH_mm_ss, dateValue);
	}

	/**
	 * @decription 根据指定格式把字符串转换成 Date 类型数据
	 * Parse a string and return the date value in the specified format
	 * @parameter 日期格式 ,如 yyyy-MM-dd HH:mm:ss
	 * @parameter 时间字符串 ,如2019-03-22 11:11:11
	 * @return
	 * @throws ParseException 
	 * @version 1.0.0
	 */
	public static Date parseDate(String strFormat, String dateValue) throws ParseException {
		if (dateValue == null)
			return null;

		if (strFormat == null)
			strFormat = yyyy_MM_dd_HH_mm_ss;

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;
		newDate = dateFormat.parse(dateValue);
		return newDate;
	}

	/**
	 * @decription 将 Date 类型的日期转换为默认格式（yyyy-MM-dd）的字符串
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String formatDate(Date aTs_Datetime) {
		return format(aTs_Datetime, yyyy_MM_dd);
	}

	/**
	 * @decription 将 Date 类型的日期转换为默认格式（yyyy-MM-dd hh:mm:ss）的字符串
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String formatDateTime(Date aTs_Datetime) {
		return format(aTs_Datetime, yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * @decription 将 Date 类型的日期转换为指定格式的字符串
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String format(Date aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);
		return dateFromat.format(aTs_Datetime);
	}

	/**
	 * @decription 将 Date 类型的日期转换为默认格式（hh:mm:ss）的字符串
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String formatTime(Date dateTime) {
		return format(dateTime, HH_mm_ss);
	}

	/**
	 * @decription 将 Timestamp 类型的日期转换为指定格式的字符串
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static String format(Timestamp aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;

		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);

		return dateFromat.format(aTs_Datetime);
	}


	/**
	 * @decription 计算两个日期之间相差的天数 date2-date1
	 * @parameter 
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * @Description 计算两个日期之间相差的小时数 endDate-startDate
	 * @Date 2019/7/27 0027 16:37
	 * @Author linjb
	 * @param startDate
	 * @param endDate
	 * @return int
	 */
	public static int hoursBetween(Date startDate, Date endDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 60 * 60);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * @decription 计算当前日期相对于"1977-12-01"的天数
	 * @parameter
	 * @return
	 * @throws ParseException 
	 * @throws
	 * @version 1.0.0
	 */
	public static long getRelativeDays(Date date) throws ParseException {
		Date relativeDate = DateUtil.parseDate("yyyy-MM-dd", "1977-12-01");
		return DateUtil.daysBetween(relativeDate, date);
	}

	/**
	 * @decription 获取12个月前的日期
	 * @parameter
	 * @return
	 * @throws ParseException 
	 * @version 1.0.0
	 */
	public static Date getDateBeforeTwelveMonth() throws ParseException {
		String date = "";
		Calendar cla = Calendar.getInstance();
		int year = cla.get(Calendar.YEAR) - 1;
		int month = cla.get(Calendar.MONTH) + 1;
		if (month > 9) {
			date = String.valueOf(year) + C_DATE_DIVISION + String.valueOf(month) + C_DATE_DIVISION + "01";
		} else {
			date = String.valueOf(year) + C_DATE_DIVISION + "0" + String.valueOf(month) + C_DATE_DIVISION + "01";
		}
		Date dateBefore = parseDate(date);
		return dateBefore;
	}

	/**
	 * @decription 取得指定日期N天后的日期
	 * @parameter
	 * @return
	 * @throws
	 * @version 1.0.0
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
	/**
	 * @decription 取得指定日期N天后的日期
	 * @parameter 日期字符串格式 yyyy-MM-dd
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午3:49:57
	 * @version 1.0.0
	 */
	public static Date addDay(String data, int days) throws ParseException {
		return addDays(parseDate(data),days);
	}
	
	/**
	 * @decription 取指定日期后一天日期
	 * @parameter 
	 * @return 传入如期的后一天日期
	 * @throws
	 * @date 2019年3月22日 下午3:13:34
	 * @version 1.0.0
	 */
	public static Date addDay(Date date){
		String year = format(date, "yyyy");
		String month = format(date, "MM");
		String day = format(date, "dd");
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day));
		calendar.add(GregorianCalendar.DATE, 1);
		return calendar.getTime();
	}
	
	/**
	 * @decription 取指定日期后一天日期
	 * @parameter 传入时间字符串  YYYY-MM-DD
	 * @return 加一天后返回 Date
	 * @throws ParseException 
	 * @throws
	 * @version 1.0.0
	 */
	public static Date addDay(String date) throws ParseException {
		if (date == null) {
			return null;
		}
		Date tempDate = parseDate(yyyy_MM_dd, date);
		return addDay(tempDate);
	}
	
	/**
	 * @decription 取当前日期的后 N 天
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午3:44:47
	 * @version 1.0.0
	 */
	public static Date addDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}	
	
	/**
	 * @decription 取指定时间的后 N 个小时
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午3:45:18
	 * @version 1.0.0
	 */
	public static Date addHours(Date date,int hour){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.HOUR_OF_DAY, hour);
		return cd.getTime();
	}

	/**
	 * @decription 取指定时间的后 N 分钟
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午3:45:18
	 * @version 1.0.0
	 */
	public static Date addMinute(Date date,int minute){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.MINUTE, minute);
		return cd.getTime();
	}
	
	/**
	 * @decription 取指定时间的后几个小时时间 
	 * @parameter 字符串时间（yyyy-MM-dd HH:mm:ss）
	 * @return yyyy-MM-dd HH:mm:ss 格式时间
	 * @throws ParseException
	 * @date 2019年3月22日 下午3:08:49
	 * @version 1.0.0
	 */
	public static String addHours(String dateTime,int hour) throws ParseException  {
		return formatDateTime(addHours(parseDateTime(dateTime),hour));
	}
	
	/**
	 * @decription 根据制定年份和月份，获取该月天数
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:00:16
	 * @version 1.0.0
	 */
	public static int getMonthDay(int year, int month) {
		return getMonthDayByYear(year)[(--month)];
	}

	/**
	 * @decription 获取指定年份的每月天数
	 * @parameter 年份
	 * @return int[] 下标从0开始分别对应1-12月的天数
	 * @throws
	 * @date 2019年3月22日 下午3:56:43
	 * @version 1.0.0
	 */
	public static int[] getMonthDayByYear(int year) {
		int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
			monthDay[1] += 1;
		return monthDay;
	}
	
	/**
	 * @decription 两个日期天数差，日期格式默认采用 yyyy-MM-dd
	 * @parameter 日期
	 * @return
	 * @throws ParseException
	 * @date 2019年3月22日 下午4:05:36
	 * @version 1.0.0
	 */
	public static long dayDiff(String start, String end) throws ParseException {
		return dateDiff(start, end, yyyy_MM_dd)[0];
	}

	/**
	 * @decription 两个时间之差
	 * @parameter 
	 * @return long[] 下标从0开始分别对应日、时、分、秒
	 * @throws
	 * @date 2019年3月22日 下午4:10:34
	 * @version 1.0.0
	 */
	public static long[] dateDiff(String start, String end,
			String format) throws ParseException {
		return dateDiff(parseDate(format, start), parseDate(format,end));
	}
	
	/**
	 * @decription 两个时间之差，时间格式默认采用 yyyy-MM-dd HH:mm:ss
	 * @parameter
	 * @return long[] 下标从0开始分别对应日、时、分、秒
	 * @throws ParseException
	 * @date 2019年3月22日 下午4:25:19
	 * @version 1.0.0
	 */
	public static long[] dateTimeDiff(String start,String end) throws ParseException{
		return dateDiff(parseDateTime(start), parseDateTime(end));
	}

	/**
	 * @decription 两个时间之差
	 * @parameter
	 * @return ong[] 下标从0开始分别对应日、时、分、秒
	 * @throws
	 * @date 2019年3月22日 下午4:30:45
	 * @version 1.0.0
	 */
	public static long[] dateDiff(Date start,Date end){
		long diff = end.getTime()
				- start.getTime();
		long nd = 86400000L;
		long nh = 3600000L;
		long nm = 60000L;
		long ns = 1000L;
		long[] date = new long[4];
		long day = diff / nd;
		long hour = diff % nd / nh;
		long min = diff % nd % nh / nm;
		long sec = diff % nd % nh % nm / ns;
		date[0] = day;
		date[1] = hour;
		date[2] = min;
		date[3] = sec;
		return date;
	}
	
	/**
	 * @decription 获取制定日期年份
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:31:18
	 * @version 1.0.0
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @decription 获取制定日期月份
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:33:53
	 * @version 1.0.0
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @decription 获取制定日期的号
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:34:06
	 * @version 1.0.0
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @decription 获取指定时间的小时
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:37:42
	 * @version 1.0.0
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(11);
	}

	/**
	 * @decription 获取指定时间的分钟
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:37:57
	 * @version 1.0.0
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(12);
	}

	/**
	 * @decription 获取指定时间的秒钟
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:38:12
	 * @version 1.0.0
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(13);
	}

	/**
	 * @decription 获取指定日期的时间戳
	 * @parameter
	 * @return
	 * @throws
	 * @date 2019年3月22日 下午4:40:05
	 * @version 1.0.0
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * @decription 获取当前星期
	 * @parameter
	 * @return 星期日为0
	 * @throws
	 * @date 2019年3月22日 下午4:51:12
	 * @version 1.0.0
	 */
	public static final int getCurrentDayOfWeek() {
		return Calendar.getInstance().get(7) -1;
	}
	
	/**
	 * @decription 获取指定日期的星期
	 * @parameter
	 * @return 星期日为0
	 * @throws
	 * @date 2019年3月22日 下午4:51:46
	 * @version 1.0.0
	 */
	public static final int getDayOfWeek(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 * @decription 获取指定日期的星期
	 * @parameter
	 * @return 星期日为0
	 * @throws ParseException
	 * @date 2019年3月22日 下午4:52:30
	 * @version 1.0.0
	 */
	public static final int getDayOfWeek(String date,String formate) throws ParseException{
		return getDayOfWeek(parseDate(date,formate));
	}

	/**
	 * 返回当天零点的时间
	 *
	 * @param date 日期
	 * @return 当天零点
	 */
	public static Date thatDay(Date date) {
		if (date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 返回当天23点59分59秒的时间
	 *
	 * @param date 日期
	 * @return 当天零点
	 */
	public static Date thatDayEnd(Date date) {
		if (date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 返回次日零点的时间
	 *
	 * @param date 日期
	 * @return 当天零点
	 */
	public static Date nextDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);

		return thatDay(cal.getTime());
	}

	/**
	 * 获取指定日期的上一个月份
	 * @return
	 */
	public static String getUpMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		Date upDate = cal.getTime();
		return format(upDate,yyyy_MM);
	}

	/**
	 * 获取当前日期的上一个月份
	 * @return
	 */
	public static String getUpMonth(){
		return getUpMonth(new Date());
	}
	
	public static Date formatDateTime(Long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(time);
		try {
			Date date = format.parse(dateStr);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
}