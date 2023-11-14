package com.example.YpStorage.controller;


import com.example.YpStorage.service.impl.MinioServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
@NoArgsConstructor
public class MinioFileController {
    @Autowired
    private MinioServiceImpl minioService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile[] file) {
        minioService.uploadFile(file);
        return "redirect:/home";
    }
    @PostMapping("/remove")
    public String removeObject(@RequestParam ("objectName") String objectName) {
        minioService.removeObject(objectName);
        return "redirect:/home";
    }


    @GetMapping("/download")
    public String downloadObject(@RequestParam String objectName) {
        minioService.downloadObject(objectName);
        return "redirect:/home";
    }

    @PostMapping("/rename")
    public String renameObject (@RequestParam("pastPath") String pastPath, @RequestParam("futurePath") String futurePath) {
        minioService.renameObject(pastPath,futurePath);
        return "redirect:/home";
    }

}
