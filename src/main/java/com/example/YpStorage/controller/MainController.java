package com.example.YpStorage.controller;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    @Autowired
    private final MinioService minioService;

    @GetMapping("")
    public String index(Model model,@RequestParam(value = "path",required = false) String path) {
        List<ObjectDto> objectDtoList = minioService.getListObjects(path);
        model.addAttribute("objects", objectDtoList);
        return "main";
    }

//    @GetMapping("/home")
//    public String home(Model model, @RequestParam(value = "path",required = false) String path) {
//        List<ObjectDto> objectDtoList = minioService.getListObjects(path);
//        model.addAttribute("objects", objectDtoList);
//        return "login";
//    }
}
