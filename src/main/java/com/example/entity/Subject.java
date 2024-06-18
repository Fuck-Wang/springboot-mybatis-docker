package com.example.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Subject {

    private Long id;

    private String name;

    private List<SubjectAndStudent> subjectAndStudents = new ArrayList<>();

}
