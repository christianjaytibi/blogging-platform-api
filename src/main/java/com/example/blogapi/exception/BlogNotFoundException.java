package com.example.blogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BlogNotFoundException extends ResponseStatusException {
    public BlogNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
