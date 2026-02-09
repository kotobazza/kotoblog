package com.kotobazza.kotoblog.posts.exceptions;

public class UnsafePostOrTextException extends RuntimeException {
    public UnsafePostOrTextException(String message) {
        super(message);
    }
}
