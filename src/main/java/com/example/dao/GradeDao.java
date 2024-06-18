package com.example.dao;

import com.example.entity.Grade;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDao {

    @Insert("insert into grade(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Grade grade);

    @Select("select * from grade where id = #{id}")
    Grade findOne(Long id);
}
