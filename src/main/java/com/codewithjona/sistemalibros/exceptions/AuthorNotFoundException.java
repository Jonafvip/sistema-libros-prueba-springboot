package com.codewithjona.sistemalibros.exceptions;


public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
