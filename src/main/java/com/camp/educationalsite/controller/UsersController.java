package com.camp.educationalsite.controller;

import java.util.List;

import javax.validation.Valid;

import com.camp.educationalsite.model.Users;
import com.camp.educationalsite.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public List<Users> getAllHandler(){
        return this.service.getAllUsers();
    }

    @GetMapping("/{username}")
    public Users getUserHandler(@PathVariable("username") String username){
        return this.service.getUser(username);
    }

    @PostMapping("/add")
    public Users uploadUserHandler(@Valid @RequestBody Users users){
        String pass = users.getPassword();
        users.setPassword(this.passwordEncoder.encode(pass));
        this.service.addUser(users);
        // String role = users.getRole();
        // users.setRole("ROLE_"+role);
        return users;
    }
    
    @DeleteMapping("/{username}")
    public void deleteUserHandler(@PathVariable("username") String username){
        this.service.deleteUser(username);
    }
}
