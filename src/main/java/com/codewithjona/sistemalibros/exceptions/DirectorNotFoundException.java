package com.codewithjona.sistemalibros.exceptions;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(String message) {
        super(message);
    }
}
