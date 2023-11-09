package com.example.YpStorage.util;

import com.example.YpStorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        long userId = user.getId();
        return "user-" + userId + "-files/";
    }
}
