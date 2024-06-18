package com.example.dao;

import com.example.entity.Grade;
import com.example.entity.Subject;
import com.example.entity.SubjectAndStudent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao {

    @Insert("insert into subject(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long save(Subject subject);

    @Select("select * from subject where id = #{id}")
    Subject findOne(Long id);

}
