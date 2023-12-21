package com.example.YpStorage.service.impl;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.repository.MinioRepository;
import com.example.YpStorage.service.MinioService;
import com.example.YpStorage.util.UserUtils;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
        return minioRepository.getListObjects(username, false);
    }

    @Override
    public void removeObject(String path) {
        if (!isDir(path)) {
            minioRepository.removeObject(path);
        } else {
           List<ObjectDto> objectDtoList = minioRepository.getListObjects(path,true);
           for (ObjectDto objectDto : objectDtoList) {
               String fullPath = objectDto.getPath(); // Тут переделать со стринг билдером что бы строка не пересоздавалась
               minioRepository.removeObject(fullPath);// при удалении последней директории с ней удаляется и корневая???
           }
        }
    }

    @Override
    public InputStream downloadObject(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
       return minioRepository.downloadObject(path);
    }

    @Override
    public void renameObject(String pastPath, String futurePath) { // изменяет имя всегда 1 объект

        futurePath = UserUtils.getUserId() + futurePath;
        minioRepository.renameObject(futurePath,pastPath);
    }

    @Override
    public boolean isDir(String path) {
         if (path.endsWith("/")) return true;
        return false;
    }
}
