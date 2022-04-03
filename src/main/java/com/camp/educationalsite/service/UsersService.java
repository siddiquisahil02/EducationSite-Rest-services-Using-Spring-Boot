package com.camp.educationalsite.service;

import java.util.List;

import com.camp.educationalsite.model.Users;
import com.camp.educationalsite.repository.UsersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo repo;

    public List<Users> getAllUsers(){
        return this.repo.findAll();
    }
    
    public Users getUser(String username){
        return this.repo.findById(username).orElse(null);
    }

    public void addUser(Users user){
        this.repo.save(user);
    }

    public void deleteUser(String username){
        this.repo.deleteById(username);
    }
}
