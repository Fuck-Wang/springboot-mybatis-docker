package com.example.serviceImp;

import com.example.dao.FileInfoDao;
import com.example.entity.domain.FileInfo;
import com.example.entity.exception.BadRequestException;
import com.example.service.FileInfoService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileInfoServiceImp implements FileInfoService {
    private final Logger log = LoggerFactory.getLogger(FileInfoServiceImp.class);

    @Resource
    private GridFsTemplate gridFsTemplate;

//    @Resource
//    private GridFSBucket gridFSBucket;

    @Resource
    private FileInfoDao fileInfoDao;

    @Override
    public void upload(MultipartFile file) {
        long size = file.getSize();
        String filename = file.getOriginalFilename();
        String suffix = null;
        if (StringUtils.isNotBlank(filename) && filename.contains(".")) {
            suffix = filename.substring(filename.lastIndexOf("."));
        }
        ObjectId mongoId;
        try {
            mongoId = gridFsTemplate.store(file.getInputStream(), filename, file.getContentType());
        } catch (Exception ex) {
            log.error("文件上传失败原因,message:{}, cause:{}", ex.getMessage(), ex.getCause());
            throw new BadRequestException(400, "FileInfoServiceImp", "文件上传失败");
        }
        FileInfo fileInfo = new FileInfo()
                .setMongoId(mongoId.toString()).setFileName(filename)
                .setFileSize(size).setFileSuffix(suffix);
        fileInfoDao.save(fileInfo);

    }

}
