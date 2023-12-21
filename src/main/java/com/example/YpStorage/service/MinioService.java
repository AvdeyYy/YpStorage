package com.example.YpStorage.service;

import com.example.YpStorage.dto.ObjectDto;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MinioService {
    void uploadFile(MultipartFile[] multipartFiles );
    void createEmptyFolder(String folderName);
    List<ObjectDto> getListObjects(String subdir);
    void removeObject(String path);
    InputStream downloadObject(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    void renameObject(String pastPath, String futurePath);
    boolean isDir(String path);

}
