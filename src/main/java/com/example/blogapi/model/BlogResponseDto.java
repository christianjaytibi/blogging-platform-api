package com.example.blogapi.model;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BlogResponseDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private Set<String> tags;
    private Instant createdAt;
    private Instant updatedAt;
}
