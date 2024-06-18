package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.core.annotation.AliasFor;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Grade {

    private Long id;

    private String name;

    private List<Student> students = new ArrayList<>();

}
