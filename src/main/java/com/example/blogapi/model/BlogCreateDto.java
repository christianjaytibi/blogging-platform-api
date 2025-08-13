package com.example.blogapi.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlogCreateDto {
    private String title;
    private String content;
    private String category;
    private Set<String> tags;
}
