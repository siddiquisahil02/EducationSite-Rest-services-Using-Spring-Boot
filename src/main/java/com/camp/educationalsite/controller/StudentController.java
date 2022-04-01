package com.camp.educationalsite.controller;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import com.camp.educationalsite.model.Courses;
import com.camp.educationalsite.model.ExamSchedule;
import com.camp.educationalsite.model.Student;
import com.camp.educationalsite.repository.ExamSchduleRepo;
import com.camp.educationalsite.service.CoursesService;
import com.camp.educationalsite.service.StudentService;
import com.camp.educationalsite.utils.AddCourse;
import com.camp.educationalsite.utils.Timetable;
import com.camp.educationalsite.utils.Upload.UploadAttachments;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private ExamSchduleRepo examRepo;

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UploadAttachments controller;

    @GetMapping("/all")
    public List<ObjectNode> getAllStudents(){
        List<ObjectNode> data = new ArrayList<>();
        List<Student> students =  this.service.getAll();
        for(Student s:students){
            ObjectNode temp = mapper.createObjectNode();
            temp.put("sID",s.getsID());
            temp.put("name",s.getName());
            temp.put("batchNo",s.getBatchNo());
            temp.put("hallTicketURL",s.getHallticketURL()); 
            ExamSchedule e = this.examRepo.findTopByOrderByIdDesc();
            if(e!=null)
                temp.put("examSchedule",e.getUrl());
            else
                temp.set("examSchedule",null);
            List<Courses> courseList = new ArrayList<>();
            s.getCourses().forEach((t)->{
                Courses c = this.coursesService.getACourse(t);
                if(c!=null){
                    courseList.add(c);
                }
            });
            JsonNode tempList = mapper.convertValue(courseList,JsonNode.class);
            temp.set("courses",tempList);
            data.add(temp);
        }
        return data;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectNode> getAStudent(@PathVariable("id") int id){
        Student s = this.service.getOne(id);
        ObjectNode temp = mapper.createObjectNode();
        if(s!=null){
            temp.put("sID",s.getsID());
            temp.put("name",s.getName());
            temp.put("batchNo",s.getBatchNo());
            temp.put("hallticketURL",s.getHallticketURL()); 
            List<Courses> courseList = new ArrayList<>();
            s.getCourses().forEach((t)->{
            Courses c = this.coursesService.getACourse(t);
            if(c!=null){
                courseList.add(c);
            }
            });
            JsonNode tempList = mapper.convertValue(courseList,JsonNode.class);
            temp.set("courses",tempList);
            return new ResponseEntity<ObjectNode>(temp,HttpStatus.OK);
        }
        temp.put("code",404);
		temp.put("message","No Record found");
		return new ResponseEntity<ObjectNode>(temp,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/timetable/{id}")
    public ResponseEntity<ObjectNode> getTimeTable(@PathVariable("id") int id){
        Student s = this.service.getOne(id);
        ObjectNode temp = mapper.createObjectNode();
        List<Courses> courseList = new ArrayList<>();
        if(s!=null){
            s.getCourses().forEach((t)->{
                Courses course = this.coursesService.getACourse(t);
                courseList.add(course);
            });
            String[] weekDays = new String[] {"monday","tuesday","wednesday","thursday","friday","saturday"};
            for(String day:weekDays){
                List<Timetable> data = new ArrayList<>();
                for(Courses c:courseList){
                    Timetable timetable = new Timetable();
                    timetable.setcID(c.getcID());
                    timetable.setName(c.getName());
                    timetable.setTrainerName(c.getTrainerName());
                    timetable.setDuration(c.getClassDuration());
                    String from = c.getSchedule().get(day);
                    timetable.setFrom(from);
                    DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                                                .parseCaseInsensitive()
                                                .appendPattern("hh:mm a")
                                                .toFormatter(Locale.ENGLISH);
                    LocalTime thisTime = LocalTime.parse(from,dateFormatter);
                    LocalTime toTime = thisTime.plusMinutes(c.getClassDuration());
                    String to = toTime.format(dateFormatter);
                    timetable.setTo(to);
                    data.add(timetable);
                }
                Collections.sort(data);
                JsonNode jsonNode = mapper.convertValue(data, JsonNode.class);
                temp.set(day,jsonNode);
            }
            return new ResponseEntity<ObjectNode>(temp,HttpStatus.OK);
        }
        temp.put("code",404);
		temp.put("message","No Record found");
		return new ResponseEntity<ObjectNode>(temp,HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/add")
    public ResponseEntity<ObjectNode> add(@Valid @RequestBody Student student){
        Set<String> courses = student.getCourses();
        ObjectNode node = mapper.createObjectNode();
        for(String s:courses){
            if(this.coursesService.getACourse(s)==null){
                node.put("code",400);
                node.put("message","Course - "+s+", doesn't exist.");
                return new ResponseEntity<ObjectNode>(node,HttpStatus.BAD_REQUEST);
            }
        }
        this.service.addStudent(student);
        node.put("code",200);
        node.put("message","Record successfully added.");
        return new ResponseEntity<ObjectNode>(node,HttpStatus.OK);
    }

    @PutMapping("/addCourse/{id}")
    public ResponseEntity<ObjectNode> addCourse(@PathVariable("id") int sID,@Valid @RequestBody AddCourse body){
        ObjectNode node = mapper.createObjectNode();
        String course = body.getCourse();
        System.out.println(course);
        Student s = this.service.getOne(sID);
        if(s!=null){
            Courses c = this.coursesService.getACourse(course);
            if(c!=null){
                Set<String> co = s.getCourses();
                co.add(course);
                s.setCourses(co);
                this.service.addStudent(s);
                node.put("code",200);
                node.put("message","Course Added successfully.");
                return new ResponseEntity<ObjectNode>(node,HttpStatus.OK);
            }
            node.put("code",400);
            node.put("message","This course doesn't exist.");
            return new ResponseEntity<ObjectNode>(node,HttpStatus.BAD_REQUEST);
        }
        node.put("code",404);
        node.put("message","Record not found.");
        return new ResponseEntity<ObjectNode>(node,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> del(@PathVariable("id") int id){
        ObjectNode objectNode = mapper.createObjectNode();
        try {
			this.service.deleteStudent(id);
			objectNode.put("code",200);
			objectNode.put("message","Record Deleted Succesfully..!");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.OK);
		} catch (Exception e) {
			objectNode.put("code",404);
			objectNode.put("message","No Record found");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
		}
    }

    @PutMapping("/uploadHallTicket/{id}")
    public ResponseEntity<?> uploadHalticket(@PathVariable("id") int id, @RequestParam(value = "file",required = true) MultipartFile file){
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            ObjectNode node =  this.controller.addHallTicket(file.getOriginalFilename(), file);
            
            String url = node.get("url").asText();
            if(url!=null){
                this.service.updateHallTicketUrl(id, url);
            }else{
                return new ResponseEntity<ObjectNode>(node,HttpStatus.NO_CONTENT);
            }
            objectNode.put("code",200);
            objectNode.put("message","HallTicket Added succesfully.");
            return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.OK);
        } catch (Exception e) {
            objectNode.put("code",404);
			objectNode.put("message","No Record found");
			return new ResponseEntity<ObjectNode>(objectNode,HttpStatus.NOT_FOUND);
        }
    }
}
