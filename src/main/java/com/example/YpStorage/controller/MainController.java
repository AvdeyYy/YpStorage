package com.example.YpStorage.controller;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.service.MinioService;
import com.example.YpStorage.util.MinioUtils;
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
    public String index(Model model,@RequestParam(value = "path", defaultValue = "", required = false) String path) {
        List<ObjectDto> objectDtoList = minioService.getListObjects(path);
        model.addAttribute("objects", objectDtoList);
        model.addAttribute("breadcrumbs", MinioUtils.getPathForBreadcrumb(path));
        model.addAttribute("breadcrumbsFolder", MinioUtils.getPathForBreadcrumbFolders(path));
        return "main";
    }
}
