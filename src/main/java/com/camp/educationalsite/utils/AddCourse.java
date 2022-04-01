package com.camp.educationalsite.utils;

import javax.validation.constraints.NotBlank;

public class AddCourse {
    
    @NotBlank
    String course;

    
    public AddCourse() {
    }


    public AddCourse(@NotBlank String course) {
        this.course = course;
    }


    public String getCourse() {
        return course;
    }


    public void setCourse(String course) {
        this.course = course;
    }
    
    
    
}
