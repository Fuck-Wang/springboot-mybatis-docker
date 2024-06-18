package com.example.controller;

import com.example.entity.Grade;
import com.example.entity.Student;
import com.example.entity.domain.Page;
import com.example.entity.exception.BadRequestException;
import com.example.service.StudentService;
import com.example.serviceImp.StudentServiceImp;
import com.mongodb.client.gridfs.GridFSBucket;
import jakarta.annotation.Resource;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

//    @Resource
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public List<Grade> saveAll(@RequestBody List<Grade> grades) {
        return studentService.saveAll(grades);
    }

    @GetMapping("/students")
    public Page<Student> findByPageIndex(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        if (pageNum <= 0 || pageSize <= 0) {
            throw new BadRequestException(400, "Student", "页码或数量错误");
        }
        return studentService.findByPageIndex(pageNum, pageSize);
    }

}
