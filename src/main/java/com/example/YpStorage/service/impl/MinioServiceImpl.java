package com.example.YpStorage.service.impl;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.repository.MinioRepository;
import com.example.YpStorage.service.MinioService;
import com.example.YpStorage.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    @Autowired
    private final MinioRepository minioRepository;

    @Override
    public void uploadFile(MultipartFile[] multipartFiles) {
        minioRepository.uploadFile(multipartFiles);
    }

    @Override
    public void createEmptyFolder(String folderName) {
        minioRepository.createEmptyFolder(folderName);
    }

    @Override
    public List<ObjectDto> getListsObjects() {
        return minioRepository.getListsObjects();
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
