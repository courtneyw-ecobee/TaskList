package com.example.courtneyw.tasklist.task;

/**
 * Created by courtney.w on 11/10/17.
 */

public class DateEntity {
    private final int day;
    private final int month;
    private final int year;

    public DateEntity(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    //todo:write unit test
    public String convertDateToString(){
        String dateString = month + "/" +day + "/" + year;
        return dateString;
    }
}
