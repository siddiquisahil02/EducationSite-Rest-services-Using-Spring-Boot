package com.camp.educationalsite.service;

import java.util.List;

import com.camp.educationalsite.model.Notice;
import com.camp.educationalsite.repository.NoticeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepo noticeRepo;

    public List<Notice> getAllNotice(String cID){
        return this.noticeRepo.findAllBycID(cID,Sort.by(Sort.Direction.DESC,"date"));  
    }

    public void add(Notice notice){
        this.noticeRepo.save(notice);
    }

    public void deletNotice(int nID){
        this.noticeRepo.deleteById(nID);
    }
}
