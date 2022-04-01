package com.camp.educationalsite.repository;

import com.camp.educationalsite.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer>{

    
    
}
