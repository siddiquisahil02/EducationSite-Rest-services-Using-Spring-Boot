package com.camp.educationalsite.repository;

import com.camp.educationalsite.model.ExamSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSchduleRepo extends JpaRepository<ExamSchedule,Integer> {

    ExamSchedule findTopByOrderByIdDesc();
    
}
