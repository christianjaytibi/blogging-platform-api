package com.example.blogapi.exception;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException() {
        super("Blog Not Found");
    }
}
