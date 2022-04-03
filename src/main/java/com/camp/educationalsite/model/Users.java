package com.camp.educationalsite.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Users {
    @Id
    String username;

    @NotBlank
    String password;

    @Pattern(regexp="^(ADMIN|FACULTY)$",message="invalid code")
    String role;

    @NotBlank
    @Size(min = 3)
    String name;

    @NotBlank
    @Size(min = 10,max = 13,message = "Invalid Phone Number.")
    String phoneNumber;

    public Users() {
    }

    public Users(String username, @NotBlank String password,
            @Pattern(regexp = "^(ADMIN|FACULTY)$", message = "invalid code") String role,
            @NotBlank @Size(min = 3) String name,
            @NotBlank @Size(min = 10, max = 13, message = "Invalid Phone Number.") String phoneNumber) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
