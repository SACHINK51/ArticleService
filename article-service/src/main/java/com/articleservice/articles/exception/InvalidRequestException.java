package com.articleservice.articles.exception;

/**
 * Custom exception thrown when an invalid request is made.
 * This may include missing required parameters, empty values, or invalid formats.
 * Extends RuntimeException to allow unchecked exception handling.
 */
public class InvalidRequestException extends RuntimeException {

    /**
     * Constructor for InvalidRequestException with 'message' error message describing the exception.
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}
