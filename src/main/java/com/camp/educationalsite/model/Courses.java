package com.camp.educationalsite.model;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Courses {


    @Id
    @Size(min = 3,message = "Course ID should be more than 2 characters long")
    String cID;

    @NotBlank
    @Size(min = 3,message = "Name should be more than 2 characters long")
    String name;

    @Min(0)
    int duration;

    @Min(value = 5,message = "Minimum Duration - 5 Minutes")
    int classDuration;
    
    @NotBlank
    @Size(min = 3,message = "Trainer name should be more then 2 characters long")
    String trainerName;

    @ElementCollection
    Map<String,String> schedule;


    
    public Courses() {} 
    public Courses(String cID, String name, int duration, int classDuration, String trainerName,
            Map<String, String> schedule) {
        this.cID = cID;
        this.name = name;
        this.duration = duration;
        this.classDuration = classDuration;
        this.trainerName = trainerName;
        this.schedule = schedule;
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
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getTrainerName() {
        return trainerName;
    }
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
    public Map<String, String> getSchedule() {
        return schedule;
    }
    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }   
    public int getClassDuration() {
        return classDuration;
    }
    public void setClassDuration(int classDuration) {
        this.classDuration = classDuration;
    }
    
    @Override
    public String toString() {
        return "Courses [cID=" + cID + ", classDuration=" + classDuration + ", duration=" + duration + ", name=" + name
                + ", schedule=" + schedule + ", trainerName=" + trainerName + "]";
    }
}
