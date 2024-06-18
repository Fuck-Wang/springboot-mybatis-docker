package com.example.controller;

import com.example.serviceImp.FileInfoServiceImp;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class FileInfoController {


    @Resource
    private FileInfoServiceImp fileInfoServiceImp;

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private GridFSBucket gridFSBucket;

    @PostMapping("/fileInfos")
    public ResponseEntity<Void> upload(@RequestPart MultipartFile file) {
        fileInfoServiceImp.upload(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fileInfos")
    public void download(HttpServletResponse response, @RequestParam("mongoId") String mongoId) throws Exception{
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(mongoId)));
        if (gridFSFile != null) {
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            String contentType = gridFsResource.getContentType();
            String filename = gridFsResource.getFilename();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            IOUtils.copy(gridFsResource.getInputStream(), response.getOutputStream());
        }

    }

}
