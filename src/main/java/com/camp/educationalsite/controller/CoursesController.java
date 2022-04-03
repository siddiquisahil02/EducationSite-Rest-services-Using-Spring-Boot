package com.camp.educationalsite.controller;

import java.util.List;

import javax.validation.Valid;

import com.camp.educationalsite.model.Courses;
import com.camp.educationalsite.service.CoursesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private CoursesService service;

    @Autowired
    private ObjectMapper mapper;
    
    @GetMapping("/public/all")
    public List<Courses> getAllCourses(){
        return this.service.getAll();
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ObjectNode> getACourses(@PathVariable("id") String id){
        Courses course =  this.service.getACourse(id);
        if(course==null){
            ObjectNode objectNode = mapper.createObjectNode();
			objectNode.put("code",404);
			objectNode.put("message","No Record found");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
        }
        ObjectNode objectNode = mapper.convertValue(course,ObjectNode.class);
		return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ObjectNode> add( @Valid @RequestBody Courses courses){
        this.service.add(courses);
        ObjectNode node = mapper.createObjectNode();
        node.put("code",200);
        node.put("message","Record Added Succesfully.");
        return new ResponseEntity<ObjectNode>(node,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> del(@PathVariable("id") String cID){
        ObjectNode objectNode = mapper.createObjectNode();
        try {
			this.service.deleteRecord(cID);
			objectNode.put("code",200);
			objectNode.put("message","Record Deleted Succesfully..!");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.OK);
		} catch (Exception e) {
			objectNode.put("code",404);
			objectNode.put("message","No Record found");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
		}
    }
    
    //TO-DO: Generate a Update course method - @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('FACULTY')")
}
