package com.camp.educationalsite.controller;

import java.util.List;

import javax.validation.Valid;

import com.camp.educationalsite.model.Users;
import com.camp.educationalsite.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Users> getAllHandler(){
        return this.service.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allAdmin")
    public List<Users> getAllAdminHandler(){
        return this.service.getAllAdmins();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allFaculty")
    public List<Users> getAllFacultyHandler(){
        return this.service.getAllFaculty();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public Users getUserHandler(@PathVariable("username") String username){
        return this.service.getUser(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdminHandler(@Valid @RequestBody Users users){
        Users u = this.service.getUser(users.getUsername());
        if(u!=null){
            ObjectNode node = new ObjectMapper().createObjectNode();
            node.put("code",400);
            node.put("message","Username already exist.");
            return new ResponseEntity<ObjectNode>(node,HttpStatus.BAD_REQUEST);
        }
        String pass = users.getPassword();
        users.setPassword(this.passwordEncoder.encode(pass));
        users.setRole("ADMIN");
        this.service.addUser(users);
        return new ResponseEntity<Users>(users,HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addFaculty")
    public ResponseEntity<?> addFacultyHandler(@Valid @RequestBody Users users){
        Users u = this.service.getUser(users.getUsername());
        if(u!=null){
            ObjectNode node = new ObjectMapper().createObjectNode();
            node.put("code",400);
            node.put("message","Username already exist.");
            return new ResponseEntity<ObjectNode>(node,HttpStatus.BAD_REQUEST);
        }
        String pass = users.getPassword();
        users.setPassword(this.passwordEncoder.encode(pass));
        users.setRole("FACULTY");
        this.service.addUser(users);
        return new ResponseEntity<Users>(users,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserHandler(@PathVariable("username") String username){
        ObjectNode node = new ObjectMapper().createObjectNode();
        try {
            this.service.deleteUser(username);
            node.put("code",200);
            node.put("message","Record deleted succesfully.");  
            return new ResponseEntity<ObjectNode>(node,HttpStatus.OK);
        } catch (Exception e) {
            node.put("code",404);
            node.put("message","No record found.");  
            return new ResponseEntity<ObjectNode>(node,HttpStatus.NOT_FOUND);
        }
    }
}
