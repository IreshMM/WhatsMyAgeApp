package com.ireshmdev.whatsmyage.model;

public class Duration {
    private int years;
    private int months;
    private int days;

    public Duration() {
    }

    public Duration(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return years == duration.years &&
                months == duration.months &&
                days == duration.days;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "years=" + years +
                ", months=" + months +
                ", days=" + days +
                '}';
    }
}
