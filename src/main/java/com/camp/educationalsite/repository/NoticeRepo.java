package com.camp.educationalsite.repository;

import java.util.List;

import com.camp.educationalsite.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

public interface NoticeRepo extends JpaRepository<Notice,Integer> {

    List<Notice> findAllBycID(String cID, Sort sort);
    
}
