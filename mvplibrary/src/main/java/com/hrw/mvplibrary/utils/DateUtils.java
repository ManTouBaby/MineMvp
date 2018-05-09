package com.hrw.mvplibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @auther:Herw
 * @describtion:
 * @date：2017/3/30
 */

public class DateUtils {

    /**
     * 获取当前时间
     *
     * @return
     */

    public static Date getNowTimer() {
        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();
        return date;
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */

    public static long getNowMilli() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 不足两位的数的添加0
     *
     * @param time
     * @return
     */
    public static String timeAdd0(int time) {
        return time <= 9 ? "0" + time : time + "";
    }

    /**
     * 获取网络时间
     *
     * @param dateFormat "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getNetTimer(String dateFormat) {
        SimpleDateFormat dff = new SimpleDateFormat(dateFormat);
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String s = dff.format(new Date());
//        MineLog.showLog("当前时间：" + s);
        return s;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 时间戳转换成String时间
     *
     * @param milliseconds
     * @param pattern
     * @return
     */
    public static String milli2Date(long milliseconds, String pattern) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliseconds);
//        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(milliseconds);
    }


}
