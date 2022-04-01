package com.camp.educationalsite.service;

import java.util.List;

import com.camp.educationalsite.model.Student;
import com.camp.educationalsite.repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAll(){
        return this.studentRepo.findAll();
    }

    public Student getOne(int id){
        return this.studentRepo.findById(id).orElse(null);
    }
    
    public Student addStudent(Student student){
        this.studentRepo.save(student);
        return student;
    }

    public void deleteStudent(int id){
        this.studentRepo.deleteById(id);
    }

    public void updateHallTicketUrl(int id,String url){
        Student student = getOne(id);
        if(student!=null){
            student.setHallticketURL(url);
            this.studentRepo.save(student);
        }else{
            throw new EmptyResultDataAccessException(id);
        }
    }
}
