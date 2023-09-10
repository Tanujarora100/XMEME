package com.crio.starter.exceptions;

public class DuplicateMemeException extends RuntimeException {
    public DuplicateMemeException(String message) {
        super(message);
    }
}
