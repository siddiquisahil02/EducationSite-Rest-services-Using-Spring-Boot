package com.camp.educationalsite.controller;

import java.util.List;

import com.camp.educationalsite.model.ExamSchedule;
import com.camp.educationalsite.service.ExamScheduleService;
import com.camp.educationalsite.utils.Upload.UploadAttachments;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/examSchedule")
public class AdminController {

    @Autowired 
    private ExamScheduleService service;

    @Autowired
    private UploadAttachments controller;

    @GetMapping("/all")
    public List<ExamSchedule> getAllHandler(){
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneHandler(@PathVariable("id") int id){
        ExamSchedule es =  this.service.getOne(id);
        if(es==null){
            ObjectNode objectNode = new ObjectMapper().createObjectNode();
			objectNode.put("code",404);
			objectNode.put("message","No Record found");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<ExamSchedule>(es,HttpStatus.OK);
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<?> uploadExamSchedule(@RequestParam(value = "title",required = true) String title, @RequestParam(value = "file",required = true) MultipartFile file){
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        try {
            ObjectNode node =  this.controller.addHallTicket(file.getOriginalFilename(), file);
            
            String url = node.get("url").asText();
            if(url!=null){
                ExamSchedule examSchedule = new ExamSchedule();
                examSchedule.setTitle(title);
                examSchedule.setUrl(url);
                this.service.addRecord(examSchedule);
            }else{
                return new ResponseEntity<ObjectNode>(node,HttpStatus.NO_CONTENT);
            }
            objectNode.put("code",200);
            objectNode.put("message","Exam Schedule uploaded succesfully.");
            return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.OK);
        } catch (Exception e) {
            objectNode.put("code",404);
			objectNode.put("message",e.getMessage());
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> del(@PathVariable("id") int id){
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        try {
			this.service.deleteRecord(id);
			objectNode.put("code",200);
			objectNode.put("message","Record Deleted Succesfully..!");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.OK);
		} catch (Exception e) {
			objectNode.put("code",404);
			objectNode.put("message","No Record found");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
		}
    }

}
