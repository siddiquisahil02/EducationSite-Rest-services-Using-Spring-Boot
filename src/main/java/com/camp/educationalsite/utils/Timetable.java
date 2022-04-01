package com.camp.educationalsite.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Timetable implements Comparable<Timetable>{

    String cID;
    String name;
    String trainerName;
    String from;
    String to;
    int duration;

    
    public Timetable() {
    }
    

    public Timetable(String cID, String name, String trainerName, String from, String to, int duration) {
        this.cID = cID;
        this.name = name;
        this.trainerName = trainerName;
        this.from = from;
        this.to = to;
        this.duration = duration;
    }


    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTo() {
        return to;
    }


    public void setTo(String to) {
        this.to = to;
    }


    @Override
    public int compareTo(Timetable o) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                                                .parseCaseInsensitive()
                                                .appendPattern("hh:mm a")
                                                .toFormatter(Locale.ENGLISH);
        LocalTime thisTime = LocalTime.parse(this.from,dateFormatter);
        LocalTime otherTime = LocalTime.parse(o.from,dateFormatter);
        return thisTime.compareTo(otherTime);
    }
}
