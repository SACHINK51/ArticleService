package com.articleservice.articles.exception;

/**
 * Custom exception thrown when an article is not found in the database.
 * Extends RuntimeException to allow unchecked exception handling.
 */
public class ArticleNotFoundException extends RuntimeException {

    // Constructor for ArticleNotFoundException with 'message' The error message describing the exception.
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
