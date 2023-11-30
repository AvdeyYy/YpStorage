package com.example.YpStorage.util;



import java.nio.file.Paths;

public class MinioUtils {
    public static String cutStart(String path) {
        return path.substring(path.indexOf("/") + 1);
    }

    public static String getName(String path) {
        return Paths.get(path).getFileName().toString();
    }

//    public static String cutPrefixName(String path) {
//        if ()
//    }
}
