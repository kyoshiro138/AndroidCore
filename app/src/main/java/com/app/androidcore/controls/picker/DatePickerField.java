package com.app.androidcore.controls.picker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class DatePickerField extends RelativeLayout implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    protected abstract int getPickerLayoutResource();
    protected abstract String getDateFormat();

    private DatePickerDialog mPickerDialog;

    private Date mDate;
    private Calendar mCalendar;
    private SimpleDateFormat mDateFormat;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
        mCalendar.setTime(date);
        String dateString = mDateFormat.format(mDate);
        onUpdateText(dateString);

        if(mPickerDialog!=null) {
            mPickerDialog.updateDate(getYear(),getMonth(),getDay());
        }
    }

    public int getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return mCalendar.get(Calendar.MONTH);
    }

    public int getDay() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public DatePickerField(Context context) {
        super(context);
        initPicker(context);
    }

    public DatePickerField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPicker(context);
    }

    public DatePickerField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPicker(context);
    }

    protected void initPicker(Context context) {
        inflate(context, getPickerLayoutResource(), this);

        mDate = new Date(System.currentTimeMillis());
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        mDateFormat = new SimpleDateFormat(getDateFormat(), Locale.getDefault());

        setClickable(true);
        setOnClickListener(this);
    }

    public void setCustomPickerDialog(DatePickerDialog dialog) {
        mPickerDialog = dialog;
        mPickerDialog.updateDate(getYear(), getMonth(), getDay());
    }

    @Override
    public void onClick(View v) {
        if(mPickerDialog == null) {
            mPickerDialog = new DatePickerDialog(getContext(), this, getYear(), getMonth(), getDay());
        }

        mPickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mCalendar.set(year, monthOfYear, dayOfMonth);
        mDate.setTime(mCalendar.getTimeInMillis());

        String dateString = mDateFormat.format(mDate);
        onUpdateText(dateString);
    }

    protected  abstract void onUpdateText(String dateString);
}
