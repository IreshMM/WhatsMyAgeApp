package com.ireshmdev.whatsmyage.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ireshmdev.whatsmyage.model.Duration;
import com.ireshmdev.whatsmyage.viewmodel.util.Util;

import java.util.Calendar;

public class MainActivityViewModel extends ViewModel {
    private Calendar birthday;
    private Calendar presentDay;
    private MutableLiveData<Duration> duration;

    {
        birthday = Calendar.getInstance();
        presentDay = Calendar.getInstance();
    }

    public LiveData<Duration> getDuration() {
        return duration;
    }

    public void setBirthday(Calendar date) {
        birthday = date;
        setDuration();
    }

    public void setPresentDay(Calendar date) {
        presentDay = date;
        setDuration();
    }

    private void setDuration() {
        Duration duration = Util.getAge(birthday, presentDay);
        this.duration.setValue(duration);
    }
}