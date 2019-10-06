package com.michaelirick.wguscheduler;

import android.app.ActionBar;
import android.arch.persistence.room.TypeConverter;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static Long now() {
        return System.currentTimeMillis() / 1000L;
    }

    public static Date fieldsToDate(DatePicker datePicker, TimePicker timePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        return calendar.getTime();
    }

    public static Long timeFromTimePicker(TimePicker timePicker) {
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();
        int h = timePicker.getHour() - mGMTOffset;
        int m = timePicker.getMinute();
        Long t = new Long(0);
        t += h * 60 * 60 * 1000; // hours
        t += m * 60 * 1000; // minutes

        return t;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }

    public static DateTimeFormatter dateTimeFormat() {
        return DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    }

    public static void setDateText(TextView view, Date date) {
        Log.d("test", "" + date);
        if(date == null) {

            view.setText("Not set");
        } else view.setText(new SimpleDateFormat("MM/dd/yyyy").format(date));
    }

    public static void setDateTimeText(TextView view, Date date) {
         Log.d("test", "" + date);
        if(date == null) {

            view.setText("Not set");
        } else {
            LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            view.setText(localDate.format(dateTimeFormat()));
        }
    }

    public static void setDatePickerValue(DatePicker picker, Date d) {
        Calendar c = new GregorianCalendar();
        if(d != null)
            c.setTime(d);
        picker.updateDate(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
    }

    public static void setTimePickerValue(TimePicker picker, Date d) {
        Calendar c = new GregorianCalendar();
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();
        if(d != null)
            c.setTime(d);
        picker.setHour(c.get(Calendar.HOUR));
        picker.setMinute(c.get(Calendar.MINUTE));
    }

    public static void setDatePickerValue(DatePicker picker, Long time) {
        Date d = fromTimestamp(time);
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        picker.updateDate(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
    }

    public static void expand(final View v) {
        v.measure(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ActionBar.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
