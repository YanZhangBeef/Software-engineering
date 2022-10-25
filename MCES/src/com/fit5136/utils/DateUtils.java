package com.fit5136.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    /**
     * Convert Date Object into String
     * @param date Date Object
     * @return Date String in specific format
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("AET"));
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * Convert String into Date Object
     * @param dateStr
     * @return date object
     */
    public static Date stringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("AET"));
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Calculate the expired date of an ad
     * @param date Post date
     * @return Expired date
     */
    public static Date calcExpiredDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 7);
        date = calendar.getTime();
        return date;
    }
}
