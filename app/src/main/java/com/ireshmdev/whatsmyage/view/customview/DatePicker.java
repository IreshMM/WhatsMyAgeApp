package com.ireshmdev.whatsmyage.view.customview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePicker extends AppCompatEditText implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private final Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    public DatePicker(Context context) {
        super(context);
        __init();
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        __init();
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        __init();
    }

    private void __init() {
        this.setOnClickListener(this);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        this.setText(sdf.format(calendar.getTime()));
    }

    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    @Override
    public void onClick(View v) {
        System.out.println("DatePicker.onClick");
        new DatePickerDialog(getContext(), this, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
        if (onDateSetListener != null) {
            onDateSetListener.onDateSet(view, year, month, dayOfMonth);
        }
    }
}
