package com.example.plantplanner;

public class Event {
    private String eventDescription;
    private int day;
    private int month;
    private int year;

    int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 29};//days for each month last index is for leap years

    public Event (String str, int y, int m, int d){
        this.eventDescription = str;
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public Event(Plant p){
        this.eventDescription = p.getCommon_name();
        this.year = 2023;
        //these are just test values
        if(p.getWaterFrequency().contains("3")){//frequent = every 3-4 days
            this.month = 12;
            this.day = 3;
        } else if(p.getWaterFrequency().contains("10")){//average = once a week
            this.month = 11;
            this.day = 30;
        } else if(p.getWaterFrequency().contains("7")){//minimum = evey two weeks
            this.month = 12;
            this.day = 10;
        }
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(int year, int month, int day){
        if(year%4 == 0){//for leap year but idc right now

        }
        while(day > daysPerMonth[month-1]){
            day = day - daysPerMonth[month-1];
            if(month == 12){//december going to january
                year++;
                month = 1;
            } else {
                month++;
            }
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getDate(){
        return (year + "-" + month + "-" + day);
    }
}
