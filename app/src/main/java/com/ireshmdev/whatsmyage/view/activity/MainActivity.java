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

import com.ireshmdev.whatsmyage.R;
import com.ireshmdev.whatsmyage.model.Duration;
import com.ireshmdev.whatsmyage.view.customview.PaddedEditText;
import com.ireshmdev.whatsmyage.viewmodel.util.Util;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    PaddedEditText yearBirthday;
    PaddedEditText monthBirthday;
    PaddedEditText dayBirthday;
    ImageButton datePickerBirthday;

    PaddedEditText yearToday;
    PaddedEditText monthToday;
    PaddedEditText dayToday;
    ImageButton datePickerToday;

    TextView ageYears;
    TextView ageMonths;
    TextView ageDays;

    final Calendar calendarBirthday = Calendar.getInstance();
    final Calendar calendarToday = Calendar.getInstance();

    boolean trigger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        __initElements();
        __addListeners();
        __addOnChangeListeners();
    }

    private void __initElements() {
        System.out.println("MainActivity.__initElements");
        yearBirthday = findViewById(R.id.year_birthday);
        monthBirthday = findViewById(R.id.month_birthday);
        dayBirthday = findViewById(R.id.day_birthday);
        datePickerBirthday = findViewById(R.id.date_picker_birthday);
        yearBirthday.setPadding(4);
        monthBirthday.setPadding(2);
        dayBirthday.setPadding(2);

        yearToday = findViewById(R.id.year_today);
        monthToday = findViewById(R.id.month_today);
        dayToday = findViewById(R.id.day_today);
        datePickerToday = findViewById(R.id.date_picker_today);
        yearToday.setPadding(4);
        monthToday.setPadding(2);
        dayToday.setPadding(2);

        ageYears = findViewById(R.id.age_years);
        ageMonths = findViewById(R.id.age_months);
        ageDays = findViewById(R.id.age_days);
    }

    private void __addListeners() {
        System.out.println("MainActivity.__addListeners");
        datePickerBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.openDatePicker(MainActivity.this, calendarBirthday, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarBirthday.set(Calendar.YEAR, year);
                        calendarBirthday.set(Calendar.MONTH, month);
                        calendarBirthday.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateBirthday();
                    }
                });
            }
        });

        datePickerToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.openDatePicker(MainActivity.this, calendarToday, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarToday.set(Calendar.YEAR, year);
                        calendarToday.set(Calendar.MONTH, month);
                        calendarToday.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateToday();
                    }
                });
            }
        });
    }

    private void updateToday() {
        System.out.println("MainActivity.updateToday");
        trigger = true;
        yearToday.setText(String.valueOf(calendarToday.get(Calendar.YEAR)));
        trigger = true;
        monthToday.setText(String.valueOf(calendarToday.get(Calendar.MONTH)));
        dayToday.setText(String.valueOf(calendarToday.get(Calendar.DAY_OF_MONTH)));
    }

    private void updateBirthday() {
        System.out.println("MainActivity.updateBirthday");
        trigger = true;
        yearBirthday.setText(String.valueOf(calendarBirthday.get(Calendar.YEAR)));
        trigger = true;
        monthBirthday.setText(String.valueOf(calendarBirthday.get(Calendar.MONTH)));
        dayBirthday.setText(String.valueOf(calendarBirthday.get(Calendar.DAY_OF_MONTH)));
    }

    private void updateAge() {
        System.out.println("MainActivity.updateAge");
        Duration age = Util.getAge(calendarBirthday, calendarToday);
        ageYears.setText(String.valueOf(age.getYears()));
        ageMonths.setText(String.valueOf(age.getMonths()));
        ageDays.setText(String.valueOf(age.getDays()));
    }

    private void updateCalendars() {
        System.out.println("MainActivity.updateCalendars");
        if (trigger) {
            trigger = false;
            return;
        }
        calendarToday.set(Calendar.YEAR, getInt(yearToday));
        calendarToday.set(Calendar.MONTH, getInt(monthToday));
        calendarToday.set(Calendar.DAY_OF_MONTH, getInt(dayToday));

        calendarBirthday.set(Calendar.YEAR, getInt(yearBirthday));
        calendarBirthday.set(Calendar.MONTH, getInt(monthBirthday));
        calendarBirthday.set(Calendar.DAY_OF_MONTH, getInt(dayBirthday));
    }

    private void __addOnChangeListeners() {
        System.out.println("MainActivity.__addOnChangeListeners");
        yearBirthday.addTextChangedListener(this);
        monthBirthday.addTextChangedListener(this);
        dayBirthday.addTextChangedListener(this);

        yearToday.addTextChangedListener(this);
        monthToday.addTextChangedListener(this);
        dayToday.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        updateCalendars();
        updateAge();
    }

    private int getInt(PaddedEditText editText) {
        return editText.length() == 0 ? 0 : Integer.parseInt(editText.getText().toString());
    }
}