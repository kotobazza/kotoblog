package com.kotobazza.kotoblog.posts;

import com.kotobazza.kotoblog.posts.exceptions.PostAlreadyExistsException;
import com.kotobazza.kotoblog.posts.exceptions.UnsafePostOrTextException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class PostsExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class, PostAlreadyExistsException.class, UnsafePostOrTextException.class})
    public ResponseEntity<String> handleEntityNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
