package com.example.YpStorage.repository;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.util.MinioUtils;
import com.example.YpStorage.util.UserUtils;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MinioRepository {
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket-name}")
    private String bucketName;

    public void uploadObject(MultipartFile[] multipartFiles) {
        try {
            for (MultipartFile file : multipartFiles) {
                InputStream inputStream = new ByteArrayInputStream(file.getBytes());
                String fileName = file.getOriginalFilename();
                String objectName = StringUtils.join(UserUtils.getUserId(),"/",fileName);
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createEmptyFolder(String objectName) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(new ByteArrayInputStream(new byte[]{}),0,-1)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("file not created");
        }

    }

    public List<ObjectDto> getListObjects(String subdir, Boolean flag) {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(subdir)
                .recursive(flag)
                .build());
        List<ObjectDto> objectDtoList = new ArrayList<>();
        results.forEach( itemResult -> {
            try {
                Item item = itemResult.get();
                objectDtoList.add(ObjectDto.builder()
                                .name(MinioUtils.getName(item.objectName()))
                                .path(item.objectName())
                                .isDir(item.isDir())
                        .build()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return objectDtoList;
    }

    public void removeObject(String path) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InputStream downloadObject(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException { //TO-DO
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                    .build());
        } catch (Exception e ) {
            throw new RuntimeException();
        }
    }


    public void renameObject(String pastPath, String futurePath){
        try {
            minioClient.copyObject(CopyObjectArgs.builder()
                            .bucket(bucketName)
                            .object(pastPath)
                            .source(CopySource
                                    .builder()
                                    .bucket(bucketName)
                                    .object(futurePath)
                                    .build())
                    .build());
            removeObject(futurePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
