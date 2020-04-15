package com.codesquad.todo2.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String errorMessage) {
        super(errorMessage);
    }
}
