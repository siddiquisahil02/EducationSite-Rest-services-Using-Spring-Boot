package com.camp.educationalsite.repository;

import com.camp.educationalsite.model.Courses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepo extends JpaRepository<Courses,String>{
    
}
