package com.articleservice.articles.exception;

/**
 * Custom exception thrown when an article with the same URL already exists in the database.
 * Extends RuntimeException to allow unchecked exception handling.
 */
public class DuplicateArticleException extends RuntimeException {

    // Constructor for DuplicateArticleException with 'message' error message describing the exception.
    public DuplicateArticleException(String message) {
        super(message);
    }
}
