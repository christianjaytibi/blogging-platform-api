package com.example.blogapi.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BlogDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private Set<String> tags;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
