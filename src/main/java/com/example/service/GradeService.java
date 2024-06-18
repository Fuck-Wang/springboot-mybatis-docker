package com.example.service;

import com.example.entity.Grade;
import com.example.entity.Student;

import java.util.List;

public interface GradeService {

    public List<Grade> saveAll(List<Grade> grades);

}
