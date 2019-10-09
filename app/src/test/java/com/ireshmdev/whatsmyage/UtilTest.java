package com.ireshmdev.whatsmyage;

import com.ireshmdev.whatsmyage.model.Duration;
import com.ireshmdev.whatsmyage.viewmodel.util.Util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class UtilTest {
    Calendar calendarNow;
    Calendar calendarThen;

    @Before
    public void setUp() throws Exception {
        calendarNow = Calendar.getInstance();
        calendarThen = Calendar.getInstance();
        calendarThen.set(Calendar.YEAR, 1996);
        calendarThen.set(Calendar.MONTH, 03);
        calendarThen.set(Calendar.DAY_OF_MONTH, 29);
    }

    @Test
    public void getAge() {
        Duration age = Util.getAge(calendarThen, calendarNow);
        System.out.println("age = " + age);
        Assert.assertTrue(new Duration(23, 04, 25).equals(age));
    }
}