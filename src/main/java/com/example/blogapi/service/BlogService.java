package com.example.blogapi.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.blogapi.model.Blog;
import com.example.blogapi.model.CreateBlogDto;
import com.example.blogapi.model.Tag;
import com.example.blogapi.repository.BlogRepository;
import com.example.blogapi.repository.TagRepository;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final TagRepository tagRepository;

    public BlogService(BlogRepository blogRepository, TagRepository tagRepository) {
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(CreateBlogDto blogDto) {
        Blog newBlog = new Blog();
        newBlog.setTitle(blogDto.getTitle());
        newBlog.setContent(blogDto.getContent());
        newBlog.setCategory(blogDto.getCategory());

        Set<String> blogDtoTags = blogDto.getTags();
        if (blogDtoTags != null && !blogDtoTags.isEmpty()) {
            for (String name : blogDtoTags) {
                Tag tag = tagRepository.findByName(name)
                        .orElseGet(
                                () -> tagRepository.save(new Tag(name)));

                newBlog.getTags().add(tag);
            }
        }

        LocalDateTime now = LocalDateTime.now();
        newBlog.setCreatedAt(now);
        newBlog.setUpdatedAt(now);

        return blogRepository.save(newBlog);
    }

}