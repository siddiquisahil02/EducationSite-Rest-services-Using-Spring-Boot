package com.camp.educationalsite.utils.Error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorModel {

    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private List<Details> details;

    public ErrorModel() {
    }

    public ErrorModel(HttpStatus httpStatus, List<Details> details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
}