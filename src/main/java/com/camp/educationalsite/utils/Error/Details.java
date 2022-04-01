package com.camp.educationalsite.utils.Error;

public class Details{
    String field;
    String message;
    public Details() {
    }
    public Details(String field, String message) {
        this.field = field;
        this.message = message;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}