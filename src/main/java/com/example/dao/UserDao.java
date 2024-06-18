package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user")
    @Results({
        @Result(property = "name", column = "name"),
        @Result(property = "id", column = "id", id = true),
        @Result(property = "age", column = "age"),
    })
    List<User> findAll();

    Integer saveAll(@Param("users") List<User> users);

}
