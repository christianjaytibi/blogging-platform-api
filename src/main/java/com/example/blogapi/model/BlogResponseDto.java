package com.example.blogapi.model;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

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

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.category = blog.getCategory();
        this.tags = blog.getTags()
                .stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toSet());
        this.createdAt = blog.getCreatedAt();
        this.updatedAt = blog.getUpdatedAt();
    }
}
