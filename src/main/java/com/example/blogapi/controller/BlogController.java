package com.example.blogapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blogapi.model.Blog;
import com.example.blogapi.model.BlogCreateDto;
import com.example.blogapi.service.BlogService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Blog>> getBlogs(@RequestParam(required = false) String term) {
        if (term == null || term.isEmpty()) {
            return ResponseEntity.ok(blogService.getAllBlogs());
        }

        return ResponseEntity.ok(blogService.getBlogsByTerm(term));
    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (!blog.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(blog.get());
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogCreateDto blogDto) {
        Blog blog = blogService.createBlog(blogDto);
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
