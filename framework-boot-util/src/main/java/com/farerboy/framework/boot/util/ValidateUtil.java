package com.farerboy.framework.boot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 校验工具
 * @Author linjb
 * @Date 2019/7/3 0003 11:16
 * @Version 1.0
 */
public class ValidateUtil {

    private static final String PHONE_REGEX = "^((13[0-9])|(14（[0-7]|[9]）)|(15([0-3]|[5-9]))|(16[0-9])|(17[0-9])|(18[0-9])|(19[1,3,5,8,9]))\\d{8}$";
    private static final String DATE_REGEX = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
    private static final String[] WF = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
    private static final String[] CHECK_CODE = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final GregorianCalendar gc = new GregorianCalendar();
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    /**
     * @param phone
     * @return boolean
     * @Description 手机号合法性校验
     * @Date 2019/7/11 0011 9:26
     * @Author linjb
     */
    public static boolean isPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        Pattern p = Pattern.compile(PHONE_REGEX);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * @Description 验证邮箱的合法性
     * @Date 2019/7/11 0011 17:52
     * @Author linjb
     * @param email
     * @return boolean
     */
    public static boolean isEmail(String email){
        if( email==null ){
            return false;
        }
        Pattern p = Pattern.compile(EMAIL_REGEX);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * @param idStr
     * @return java.lang.String
     * @Description 身份证验证
     * 　　1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码
     * 　　2、地址码(前六位数）表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行
     * 　　3、出生日期码（第七位至十四位）表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符
     * 　　4、顺序码（第十五位至十七位）表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性
     * 　　5、校验码（第十八位数）
     * 　　（1）十七位数字本体码加权求和公式 S = Sum(iDCardNo * wf), i = 0, ... , 16 ，先对前17位数字的权求和 iDCardNo:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 wf: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 　　（2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
     * @Date 2019/7/11 0011 11:17
     * @Author linjb
     */
    public static boolean isIdCard(String idStr) {
        String iDCardNo = "";
        //判断号码的长度 15位或18位
        if (idStr.length() != 15 && idStr.length() != 18) {
            // 身份证号码长度应该为15位或18位
            return false;
        }
        if (idStr.length() == 18) {
            iDCardNo = idStr.substring(0, 17);
        } else if (idStr.length() == 15) {
            iDCardNo = idStr.substring(0, 6) + "19" + idStr.substring(6, 15);
        }
        if (isStrNum(iDCardNo) == false) {
            // 身份证15位号码都应为数字;18位号码除最后一位外,都应为数字
            return false;
        }
        //判断出生年月
        String strYear = iDCardNo.substring(6, 10);// 年份
        String strMonth = iDCardNo.substring(10, 12);// 月份
        String strDay = iDCardNo.substring(12, 14);// 月份
        if (isStrDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            // 身份证生日无效
            return false;
        }
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - simpleDateFormat.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                //身份证生日不在有效范围
                return false;
            }
        }catch (Exception e){
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            //身份证月份无效
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            // 身份证日期无效
            return false;
        }
        //判断地区码
        Hashtable h = GetAreaCode();
        if (h.get(iDCardNo.substring(0, 2)) == null) {
            // 身份证地区编码错误
            return false;
        }
        //判断最后一位
        int theLastOne = 0;
        for (int i = 0; i < 17; i++) {
            theLastOne = theLastOne + Integer.parseInt(String.valueOf(iDCardNo.charAt(i))) * Integer.parseInt(CHECK_CODE[i]);
        }
        int modValue = theLastOne % 11;
        String strVerifyCode = WF[modValue];
        iDCardNo = iDCardNo + strVerifyCode;
        if (idStr.length() == 18 && !iDCardNo.equals(idStr.toUpperCase())) {
            // 身份证无效，不是合法的身份证号码
            return false;
        }
        return true;

    }

    /**
     * 地区代码
     *
     * @return Hashtable
     */
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isStrNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    private static boolean isStrDate(String strDate) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
