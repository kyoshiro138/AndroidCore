package com.app.androidcore.utils;

import java.util.Calendar;
import java.util.Date;

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
}
