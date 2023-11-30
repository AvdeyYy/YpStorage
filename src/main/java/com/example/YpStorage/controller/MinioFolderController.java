package com.example.YpStorage.controller;

import com.example.YpStorage.service.impl.MinioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class MinioFolderController {

    @Autowired
    private MinioServiceImpl minioService;

    @PostMapping("/upload-emptyFolder")
    public String uploadFolder(String folderName){
        minioService.createEmptyFolder(folderName);
        return "redirect:/";
    }
    @PostMapping("/uploadFolder")
    public String uploadFolder(@RequestParam("file")MultipartFile[] files){
        minioService.uploadFile(files);
        return "redirect:/";
    }

}
