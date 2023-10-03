package com.example.YpStorage.controller;


import com.example.YpStorage.service.MinioFileService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@NoArgsConstructor
public class MinioController {
    @Autowired
    private MinioFileService minioService;

    @GetMapping("/upload")
    public String showUploadFile(){
        return "upload";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        System.out.println("fileName" + fileName);
    try {
        minioService.putObject(fileName, file.getInputStream());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "upload";
    }

//    public String downloadFile(){
//     find a way how downloadFile from minio storage
//    }


}
