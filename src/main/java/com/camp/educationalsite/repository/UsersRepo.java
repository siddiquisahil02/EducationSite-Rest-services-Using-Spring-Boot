package com.camp.educationalsite.repository;

import java.util.List;

import com.camp.educationalsite.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,String> {

    List<Users> findAllByrole(String role);
    
}
