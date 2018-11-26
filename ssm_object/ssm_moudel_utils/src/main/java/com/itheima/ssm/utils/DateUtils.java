package com.itheima.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //将Date转换成字符串
    public static String date2String(Date date,String pat){
        SimpleDateFormat sdf = new SimpleDateFormat(pat);
        String format = sdf.format(date);
        return format;
    }

    //将字符串解析成Date
    public static Date String2Date(String date,String pat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pat);
        Date parse = sdf.parse(date);
        return parse;
    }
}
