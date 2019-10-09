package com.ireshmdev.whatsmyage.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ireshmdev.whatsmyage.model.Duration;

import java.util.Calendar;
import java.util.Date;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Calendar> birthday;
    private MutableLiveData<Calendar> presentDay;
    private MutableLiveData<Duration> duration;

    {
        birthday.setValue(Calendar.getInstance());
        presentDay.setValue(Calendar.getInstance());
        duration.setValue(new Duration());
    }

    public LiveData<Calendar> getBirthday() {
        return birthday;
    }

    public LiveData<Calendar> getPresentDay() {
        return presentDay;
    }

    public LiveData<Duration> getDuration() {
        return duration;
    }

    public void setBirthday(Date date) {

    }

    public void setPresentDay(Date date) {

    }
}