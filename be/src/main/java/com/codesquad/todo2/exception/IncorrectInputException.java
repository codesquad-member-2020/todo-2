package com.codesquad.todo2.exception;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException (String errorMessage) {
        super(errorMessage);
    }
}
