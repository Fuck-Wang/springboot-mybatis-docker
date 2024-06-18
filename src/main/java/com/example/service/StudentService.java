package com.example.service;

import com.example.entity.Grade;
import com.example.entity.Student;
import com.example.entity.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {

    @Transactional
    List<Grade> saveAll(List<Grade> grades);

    Page<Student> findByPageIndex(Integer startIndex, Integer offset);
}
