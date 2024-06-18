package com.example.dao;

import com.example.entity.Student;
import com.example.entity.domain.Page;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    List<Student> saveAll(@Param("students") List<Student> students);

    @Insert("insert into student(stu_no, name, point, grade_id) values(#{stuNo}, #{name}, #{point}, #{grade.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Student student);

    void saveAl(List<Student> students);

//    @Select("select stu.*, gr.id as g_id, gr.name as g_name from student stu left join grade gr on(stu.grade_id=gr.id) limit #{startIndex}, #{offset} ")
//    @Results({
//            @Result(property = "id", column = "id", id = true),
//            @Result(property = "stuNo", column = "stu_no"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "point", column = "point"),
//            @Result(property = "grade.id", column = "g_id"),
//            @Result(property = "grade.name", column = "g_name")
//    })
//    @ResultMap("content")
    List<Student> findByPageIndex(Integer startIndex, Integer offset);

    @Select("select count(1) from student")
    int count();

}
