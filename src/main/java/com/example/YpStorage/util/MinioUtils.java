package com.example.YpStorage.util;



import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinioUtils {
    public static String getName(String path) {
        return Paths.get(path).getFileName().toString();
    }

    public static List<String> getPathForBreadcrumb(String path) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                list.add(path.substring(0, i));
            }
        }
        return list;
    }

    public static List<String> getPathForBreadcrumbFolders(String path) {
        if (path.isEmpty()) {
            return List.of(path);
        }
        return Arrays.stream(path.split("/")).toList();
    }

}
