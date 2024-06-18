package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.core.annotation.AliasFor;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Student {

    private Long id;

    private String stuNo;

    private String name;

    private Integer point;

    private Grade grade;

    private List<SubjectAndStudent> subjectAndStudents = new ArrayList<>();


}
