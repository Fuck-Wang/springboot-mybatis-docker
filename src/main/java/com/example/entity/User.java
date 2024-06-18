package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
public class User {

    private Long id;

    private String name;

    private String age;

    @CreatedDate
    private Instant createTime = Instant.now();

    private Long createById;

    private Instant updateTime;

    private Long updateById;

    private Long parentId;

}
