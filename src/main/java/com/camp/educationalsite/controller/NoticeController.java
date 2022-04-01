package com.camp.educationalsite.controller;

import java.util.List;

import javax.validation.Valid;

import com.camp.educationalsite.model.Courses;
import com.camp.educationalsite.model.Notice;
import com.camp.educationalsite.service.CoursesService;
import com.camp.educationalsite.service.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/{cID}")
    public List<Notice> getAllNoticeBycID(@PathVariable("cID") String cID){
        return this.service.getAllNotice(cID);
    }

    @PostMapping("/add")
    public ResponseEntity<ObjectNode> addNotice(@Valid @RequestBody Notice notice){
        Courses courses = this.coursesService.getACourse(notice.getcID());
        ObjectNode node = mapper.createObjectNode();
        if(courses!=null){
            this.service.add(notice);
            node.put("code",200);
            node.put("message","Record Added Succesfully.");
            return new ResponseEntity<ObjectNode>(node,HttpStatus.OK);
        }
        node.put("code",404);
        node.put("message","Course - "+notice.getcID()+", doesn't exist.");
        return new ResponseEntity<ObjectNode>(node,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteHandler(@PathVariable("id") int nID){
        try {
            this.service.deletNotice(nID);
        } catch (Exception e) {
        }
    }    
}
