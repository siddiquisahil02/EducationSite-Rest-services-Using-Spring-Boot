package com.camp.educationalsite.service;

import java.util.List;

import com.camp.educationalsite.model.Courses;
import com.camp.educationalsite.repository.CoursesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepo repo;

    public List<Courses> getAll(){
        return this.repo.findAll();
    }

    public Courses getACourse(String id){
        return this.repo.findById(id).orElse(null);
    }
    
    public void add(Courses courses){
        this.repo.save(courses);
    }

    public void deleteRecord(String cID){
        this.repo.deleteById(cID);
    }
    
}
