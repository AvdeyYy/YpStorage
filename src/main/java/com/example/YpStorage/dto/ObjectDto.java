package com.example.YpStorage.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ObjectDto {
    //1
//    private String owner;
//    private boolean isDir;
//    private String fileName;
//    private String filePath;
//    private String originalName;
    private String name;
    private String path;
    private boolean isDir;

}
