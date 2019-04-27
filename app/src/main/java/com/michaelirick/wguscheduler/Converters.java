package com.michaelirick.wguscheduler;

import android.arch.persistence.room.TypeConverter;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static void setDatePickerValue(DatePicker picker, Long time) {
        picker.updateDate(
                fromTimestamp(time).getYear(),
                fromTimestamp(time).getMonth(),
                fromTimestamp(time).getDay()
        );
    }
}
