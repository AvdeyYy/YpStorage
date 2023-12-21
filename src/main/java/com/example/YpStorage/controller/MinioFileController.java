package com.example.YpStorage.controller;


import com.example.YpStorage.service.impl.MinioServiceImpl;
import io.minio.errors.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@NoArgsConstructor
public class MinioFileController {
    @Autowired
    private MinioServiceImpl minioService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile[] file) {
        minioService.uploadFile(file);
        return "redirect:/";
    }
    @PostMapping("/remove")
    public String removeObject(@RequestParam("name") String objectName) {
        minioService.removeObject(objectName);
        return "redirect:/";
    }


    @GetMapping("/download")
    public void downloadObject(@RequestParam("name") String objectName, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream file = minioService.downloadObject(objectName);
        response.setHeader("Content-Disposition", "attachment; filename=" + file);
        response.setStatus(HttpServletResponse.SC_OK);
        try {

            FileCopyUtils.copy(file, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage()); //to-do переделать чтение всей хуйни тут без сервлета ибо нахуй он тут
        }
    }

    @PostMapping("/rename")
    public String renameObject (@RequestParam("pastPath") String pastPath, @RequestParam("futurePath") String futurePath) {
        minioService.renameObject(pastPath,futurePath);
        return "redirect:/";
    }

}
