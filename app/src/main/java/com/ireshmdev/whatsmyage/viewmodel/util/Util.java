package com.ireshmdev.whatsmyage.viewmodel.util;

import android.app.DatePickerDialog;
import android.content.Context;

import com.ireshmdev.whatsmyage.model.Duration;

import java.util.Calendar;

public class Util {
    public static void openDatePicker(Context context, Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        new DatePickerDialog(context, listener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private static int getDaysForMonth(int month) {
        switch (month) {
            case 0:
                return 31;
            case 1:
                return 28;
            case 2:
                return 31;
            case 3:
                return 30;
            case 4:
                return 31;
            case 5:
                return 30;
            case 6:
                return 31;
            case 7:
                return 31;
            case 8:
                return 30;
            case 9:
                return 31;
            case 10:
                return 30;
            case 11:
                return 31;
            default:
                return 30;
        }
    }

    public static Duration getAge(Calendar calendar1, Calendar calendar2) {
        Calendar fromCalendar;
        Calendar toCalendar;

        if (calendar1.compareTo(calendar2) < 0) {
            fromCalendar = calendar1;
            toCalendar = calendar2;
        } else {
            fromCalendar = calendar2;
            toCalendar = calendar1;
        }

        int fromYear = fromCalendar.get(Calendar.YEAR);
        int fromMonth = fromCalendar.get(Calendar.MONTH);
        int fromDay = fromCalendar.get(Calendar.DAY_OF_MONTH);

        int toYear = toCalendar.get(Calendar.YEAR);
        int toMonth = toCalendar.get(Calendar.MONTH);
        int toDay = toCalendar.get(Calendar.DAY_OF_MONTH);

        Duration duration = new Duration();

        int days = toDay - fromDay;
        if (days < 0) days += getDaysForMonth(--toMonth % 12);

        int months = toMonth - fromMonth;
        if (months < 0) {
            months += 12;
            toYear--;
        }

        int years = toYear - fromYear;

        duration.setDays(days);
        duration.setMonths(months);
        duration.setYears(years);

        return duration;
    }

}
