package com.example.YpStorage.service.impl;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.repository.MinioRepository;
import com.example.YpStorage.service.MinioService;
import com.example.YpStorage.util.MinioUtils;
import com.example.YpStorage.util.UserUtils;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    @Autowired
    private final MinioRepository minioRepository;

    @Override
    public void uploadFile(MultipartFile[] multipartFiles) {
        minioRepository.uploadObject(multipartFiles);
    }

    @Override
    public void createEmptyFolder(String folderName) {
        String objectName = StringUtils.join(UserUtils.getUserId(),folderName,"/");
        minioRepository.createEmptyFolder(objectName);
    }

    @Override
    public List<ObjectDto> getListObjects(String subdir) {
        String username = UserUtils.getUserId();
        if (subdir != null) {
            username = subdir;
        }
        return minioRepository.getListObjects(username);
    }

    @Override
    public void removeObject(String path) {
        minioRepository.removeObject(path);
    }

    @Override
    public void downloadObject(String path) {
        minioRepository.downloadObject(path);
    }

    @Override
    public void renameObject(String pastPath, String futurePath) {
        futurePath = UserUtils.getUserId() + futurePath;
        minioRepository.renameObject(futurePath,pastPath);
    }
}
