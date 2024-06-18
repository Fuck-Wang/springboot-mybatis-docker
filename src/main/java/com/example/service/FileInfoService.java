package com.example.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileInfoService {

    public void upload(MultipartFile file);

}
