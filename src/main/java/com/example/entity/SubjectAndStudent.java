package com.example.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectAndStudent {

    private Long id;

    private Subject subject;

    private Student student;

}
