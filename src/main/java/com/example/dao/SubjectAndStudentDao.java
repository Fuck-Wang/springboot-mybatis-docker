package com.example.dao;

import com.example.entity.Subject;
import com.example.entity.SubjectAndStudent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectAndStudentDao {

    @Select("select 1 from subject_and_student where subject_id = #{id} limit 1")
//    @Results({
//            @Result(property = "id", column = "id", id = true),
//            @Result(property = "subjectId", column = "subject_id"),
//            @Result(property = "studentId", column = "student_id")
//    })
    SubjectAndStudent findOne(Long id);

    void saveAll(List<SubjectAndStudent> subjectAndStudents);

}
