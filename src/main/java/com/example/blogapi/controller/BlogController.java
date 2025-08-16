package com.example.blogapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blogapi.model.BlogResponseDto;
import com.example.blogapi.model.CreateBlogRequestDTO;
import com.example.blogapi.service.BlogService;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> getBlogs(@RequestParam(required = false) String term) {
        if (term == null || term.isEmpty()) {
            return ResponseEntity.ok(blogService.getAllBlogs());
        }

        return ResponseEntity.ok(blogService.getAllBlogs(term));
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogResponseDto> getBlog(@PathVariable Long id) {
        BlogResponseDto blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }

    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@RequestBody CreateBlogRequestDTO blogDto) {
        BlogResponseDto blog = blogService.createBlog(blogDto);
        URI locationOfSavedBlog = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(blog.getId())
                .toUri();
        return ResponseEntity.created(locationOfSavedBlog).body(blog);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlogById(id);
        return ResponseEntity.noContent().build();
    }

}
