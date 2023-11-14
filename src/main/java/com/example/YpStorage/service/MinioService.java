package com.example.YpStorage.service;

import com.example.YpStorage.dto.ObjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MinioService {
    void uploadFile(MultipartFile[] multipartFiles );
    void createEmptyFolder(String folderName);
    List<ObjectDto> getListObjects();
    void removeObject(String path);
    void downloadObject(String path);
    void renameObject(String pastPath, String futurePath);

}
