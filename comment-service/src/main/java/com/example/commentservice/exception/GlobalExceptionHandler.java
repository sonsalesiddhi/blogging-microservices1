package com.example.commentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(UserNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleExceptionUserNotFound(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

@ExceptionHandler(BlogNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleExceptionBlogNotFound(BlogNotFoundException blogNotFoundException){
        return new ResponseEntity<>(blogNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }
}
