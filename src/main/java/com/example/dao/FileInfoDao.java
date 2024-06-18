package com.example.dao;

import com.example.entity.domain.FileInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoDao {

    @Insert("insert into file_info values(#{mongoId}, #{compressMongoId}, #{fileName}, " +
            "#{fileSuffix}, #{fileSize}, #{compressSize}, #{uploadUserId}, #{fileType}, #{remark})")
//    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "mongoId", before = false, resultType = String.class)
    void save(FileInfo fileInfo);

}
