package com.example.commentservice.exception;

public class BlogNotFoundException extends RuntimeException{
    public BlogNotFoundException(String message) {
        super(message);
    }
}
