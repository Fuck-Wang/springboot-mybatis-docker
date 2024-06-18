package com.example.entity.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileInfo {

    private String mongoId;

    /**
     * 压缩后mongoId
     */
    private String compressMongoId;

    private String fileName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    private Long fileSize;

    /**
     * 压缩后大小
     */
    private Long compressSize;

    /**
     * 上传人
     */
    private String uploadUserId;

    /**
     * 文件类型
     */
    private String fileType;

    private String remark;




}
