package com.articleservice.articles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling application-wide exceptions.
 * Uses @RestControllerAdvice to intercept and process exceptions thrown by controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions when an article is not found.
     * @param ex The thrown ArticleNotFoundException.
     * @return A structured error response with HTTP status 404 (Not Found).
     */
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleArticleNotFound(ArticleNotFoundException ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions when an article with the same URL already exists.
     * @param ex The thrown DuplicateArticleException.
     * @return A structured error response with HTTP status 409 (Conflict).
     */
    @ExceptionHandler(DuplicateArticleException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateArticle(DuplicateArticleException ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Handles invalid request exceptions, such as missing or incorrect input data.
     * @param ex The thrown InvalidRequestException.
     * @return A structured error response with HTTP status 400 (Bad Request).
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRequest(InvalidRequestException ex) {
        return createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions that are not explicitly caught.
     * @param ex The thrown generic Exception.
     * @return A structured error response with HTTP status 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        return createErrorResponse("An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Creates a standardized error response.
     * @param message The error message.
     * @param status  The HTTP status code to return.
     * @return A ResponseEntity containing a structured error response.
     */
    private ResponseEntity<Map<String, String>> createErrorResponse(String message, HttpStatus status) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        errorResponse.put("status", String.valueOf(status.value()));
        return new ResponseEntity<>(errorResponse, status);
    }
}
