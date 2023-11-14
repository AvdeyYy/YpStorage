package com.example.YpStorage.controller;

import com.example.YpStorage.dto.ObjectDto;
import com.example.YpStorage.model.User;
import com.example.YpStorage.repository.MinioRepository;
import com.example.YpStorage.service.MinioService;
import com.example.YpStorage.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final MinioService minioService;
    @Autowired
    private final MinioRepository minioRepository;

    @GetMapping("/home")
    public String index(Model model) {
        List<ObjectDto> objectDtoList = minioService.getListObjects();
        model.addAttribute("objects", objectDtoList);
        return "main";
    }

}
