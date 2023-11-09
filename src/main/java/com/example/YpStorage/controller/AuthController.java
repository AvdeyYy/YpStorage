package com.example.YpStorage.controller;

import com.example.YpStorage.dto.UserDto;
import com.example.YpStorage.service.MinioService;
import com.example.YpStorage.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final MinioService minioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String registrationUser(UserDto userDto) {
        userService.createUser(userDto);
        return "redirect:/login";
    }

}
