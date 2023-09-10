package com.crio.starter.exceptions;

public class MemeDoesNotExistException extends RuntimeException {
    public MemeDoesNotExistException(String message) {
        super(message);
    }
}
