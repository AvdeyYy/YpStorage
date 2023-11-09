package com.example.YpStorage.util;

public class MinioUtils {
    public static String cutStart(String path) {
        return path.substring(path.indexOf("/") + 1);
    }
}
