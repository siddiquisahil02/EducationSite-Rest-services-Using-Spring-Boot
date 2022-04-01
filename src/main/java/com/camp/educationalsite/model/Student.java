package com.camp.educationalsite.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Student {

    @Id
    @Column(nullable = false)
    int sID;

    @NotBlank
    @Size(min = 3,message = "Name should be more then 2 characters long")
    String name;

    @NotBlank
    String batchNo;

    String hallticketURL = null;

    @ElementCollection
    Set<String> courses = new HashSet<>();

    
    public Student() {
    }
    

    public Student(int sID, String name, String batchNo, String hallticketURL, Set<String> courses) {
        this.sID = sID;
        this.name = name;
        this.batchNo = batchNo;
        this.hallticketURL = hallticketURL;
        this.courses = courses;
    }


    public int getsID() {
        return sID;
    }
    public void setsID(int sID) {
        this.sID = sID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public Set<String> getCourses() {
        return courses;
    }
    public void setCourses(Set<String> courses) {
        this.courses = courses;
    }
    

    public String getHallticketURL() {
        return hallticketURL;
    }


    public void setHallticketURL(String hallticketURL) {
        this.hallticketURL = hallticketURL;
    }


}
