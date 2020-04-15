package com.codesquad.todo2.exception;

import com.codesquad.todo2.api.ResponseBodyWrapper;
import io.jsonwebtoken.security.SignatureException;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseBodyWrapper> handlerSignatureException(SignatureException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBodyWrapper.failed("유효하지 않은 토큰값입니다."));
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ResponseBodyWrapper> handlerNotFoundUserException(NotFoundUserException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBodyWrapper.failed(e.getMessage()));
    }

    @ExceptionHandler(IncorrectInputException.class)
    public ResponseEntity<ResponseBodyWrapper> handlerNotFoundUserException(IncorrectInputException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBodyWrapper.failed(e.getMessage()));
    }
}
