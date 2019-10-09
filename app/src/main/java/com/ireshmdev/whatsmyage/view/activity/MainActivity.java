package com.ireshmdev.whatsmyage.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ireshmdev.whatsmyage.R;
import com.ireshmdev.whatsmyage.view.customview.PaddedEditText;
import com.ireshmdev.whatsmyage.viewmodel.MainActivityViewModel;
import com.ireshmdev.whatsmyage.viewmodel.util.Util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;

    DateViewContainer birthday;
    DateViewContainer presentDay;

    TextView ageYears;
    TextView ageMonths;
    TextView ageDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        __initElements();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    private void __initElements() {
        System.out.println("MainActivity.__initElements");

        PaddedEditText yearBirthday = findViewById(R.id.year_birthday);
        PaddedEditText monthBirthday = findViewById(R.id.month_birthday);
        PaddedEditText dayBirthday = findViewById(R.id.day_birthday);
        ImageButton datePickerBirthday = findViewById(R.id.date_picker_birthday);
        yearBirthday.setPadding(4);
        monthBirthday.setPadding(2);
        dayBirthday.setPadding(2);
        birthday = new DateViewContainer(yearBirthday, monthBirthday, dayBirthday, datePickerBirthday, new OnChangeListener<Calendar>() {
            @Override
            public void call(Calendar calendar) {
                mainActivityViewModel.setBirthday(calendar);
            }
        });

        PaddedEditText yearToday = findViewById(R.id.year_today);
        PaddedEditText monthToday = findViewById(R.id.month_today);
        PaddedEditText dayToday = findViewById(R.id.day_today);
        ImageButton datePickerToday = findViewById(R.id.date_picker_today);
        yearToday.setPadding(4);
        monthToday.setPadding(2);
        dayToday.setPadding(2);
        presentDay = new DateViewContainer(yearToday, monthToday, dayToday, datePickerToday, new OnChangeListener<Calendar>() {
            @Override
            public void call(Calendar calendar) {
                mainActivityViewModel.setPresentDay(calendar);
            }
        });

        ageYears = findViewById(R.id.age_years);
        ageMonths = findViewById(R.id.age_months);
        ageDays = findViewById(R.id.age_days);
    }

    private int getInt(PaddedEditText editText) {
        return editText.length() == 0 ? 0 : Integer.parseInt(editText.getText().toString());
    }

    private class DateViewContainer implements DatePickerDialog.OnDateSetListener, TextWatcher, View.OnClickListener {
        private PaddedEditText year;
        private PaddedEditText month;
        private PaddedEditText day;
        private ImageButton pickerButton;
        private OnChangeListener<Calendar> onChange;
        private Calendar calendar;

        public DateViewContainer(PaddedEditText year, PaddedEditText month, PaddedEditText day, ImageButton pickerButton, OnChangeListener<Calendar> onChange) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.pickerButton = pickerButton;
            this.onChange = onChange;
            this.calendar = new GregorianCalendar();
            this.pickerButton.setOnClickListener(this);
            this.day.addTextChangedListener(this);
            this.month.addTextChangedListener(this);
            this.day.addTextChangedListener(this);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            this.year.setText(String.valueOf(year));
            this.month.setText(String.valueOf(month));
            this.day.setText(String.valueOf(dayOfMonth));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {
            calendar.set(Calendar.YEAR, getInt(year));
            calendar.set(Calendar.MONTH, getInt(month));
            calendar.set(Calendar.DAY_OF_MONTH, getInt(day));
            onChange.call(calendar);
        }

        @Override
        public void onClick(View v) {
            Util.openDatePicker(MainActivity.this, calendar, this);
        }
    }

    private interface OnChangeListener<T> {
        void call(T t);
    }
}