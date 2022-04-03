package com.camp.educationalsite.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// import com.fasterxml.jackson.annotation.JsonFormat;

// import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int nID;

    @NotBlank
    @Size(min = 3,message = "Title should be more then 2 characters long")
    String title;

    @NotBlank
    @Size(min = 3,message = "Body should be more then 2 characters long")
    String body;

    LocalDate date = LocalDateTime.now().toLocalDate();
    
    @NotBlank
    String cID;
    
    public Notice() {
    }
    public Notice(int nID, String title, String body, LocalDate date, String cID) {
        this.nID = nID;
        this.title = title;
        this.body = body;
        this.date = date;
        this.cID = cID;
    }
    public int getnID() {
        return nID;
    }
    public void setnID(int nID) {
        this.nID = nID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getcID() {
        return cID;
    }
    public void setcID(String cID) {
        this.cID = cID;
    }

    @Override
    public String toString() {
        return "Notice [body=" + body + ", cID=" + cID + ", date=" + date + ", nID=" + nID + ", title=" + title + "]";
    }
}
