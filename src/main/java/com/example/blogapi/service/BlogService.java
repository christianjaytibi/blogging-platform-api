package com.example.blogapi.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.blogapi.exception.BlogNotFoundException;
import com.example.blogapi.model.Blog;
import com.example.blogapi.model.BlogCreateDto;
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

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByTerm(String term) {
        return blogRepository.findBlogsByTerm(term);
    }

    public Blog getBlogById(Long id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            return blogOptional.get();
        }

        throw new BlogNotFoundException();
    }

    public Blog createBlog(BlogCreateDto blogDto) {
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

        Instant now = Instant.now();
        newBlog.setCreatedAt(now);
        newBlog.setUpdatedAt(now);

        return blogRepository.save(newBlog);
    }

    public void deleteBlogById(Long id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (!blogOptional.isPresent()) {
            throw new BlogNotFoundException();
        }

        blogRepository.deleteById(id);
    }

}