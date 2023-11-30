package com.example.YpStorage.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ObjectDto {
    private String name;
    private String path;
    private boolean isDir;

}
