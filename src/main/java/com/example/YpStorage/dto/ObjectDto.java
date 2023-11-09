package com.example.YpStorage.dto;

import lombok.Data;

@Data
public class ObjectDto {
    private String name;
    private String filePath;
    private long fileSize;

    public ObjectDto(String name, String filePath, long fileSize) {
        this.name = name;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
