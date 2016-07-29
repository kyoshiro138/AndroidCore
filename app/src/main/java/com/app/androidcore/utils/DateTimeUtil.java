package com.app.androidcore.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    private Calendar mCalendar;

    public DateTimeUtil() {
        mCalendar = Calendar.getInstance();
    }

    public int getYear(Date date) {
        mCalendar.setTime(date);
        return mCalendar.get(Calendar.YEAR);
    }

    public int getMonth(Date date) {
        mCalendar.setTime(date);
        return mCalendar.get(Calendar.MONTH);
    }

    public int getDayOfMonth(Date date) {
        mCalendar.setTime(date);
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public Date dateOf(String dateString, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String toString(Date date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return format.format(date);
    }
}
