package com.camp.educationalsite.service;

import java.util.List;

import com.camp.educationalsite.model.ExamSchedule;
import com.camp.educationalsite.repository.ExamSchduleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamScheduleService {

    @Autowired
    private ExamSchduleRepo repo;

    public void addRecord(ExamSchedule examSchedule){
        this.repo.save(examSchedule);
    }

    public List<ExamSchedule> getAll(){
        return this.repo.findAll();
    }

    public ExamSchedule getOne(int id){
        return this.repo.findById(id).orElse(null);
    }

    public void deleteRecord(int id){
        this.repo.deleteById(id);
    }
    
}
