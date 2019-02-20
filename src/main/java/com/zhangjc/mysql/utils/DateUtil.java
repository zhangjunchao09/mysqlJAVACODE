package com.zhangjc.mysql.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }

    public static Timestamp getCurrentTime() {
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String str = sdf.format(date1);
        return Timestamp.valueOf(str);
    }

    public static String getCurrentTimeStr() {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static String getCurrentTimeStr(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static String dateCoverterToStr(Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date strCoverterToDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(str, pos);
    }

    public static Date getNDayAfter(Integer n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    public static int getMonthLenOfYearMonth(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    public static boolean isDateAfterToday(String dateStr, String dateStrFormatter) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateStrFormatter);
        cal.setTime(sdf.parse(dateStr));
        return cal.compareTo(Calendar.getInstance()) > 0;
    }
}
