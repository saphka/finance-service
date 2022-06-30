package org.saphka.finance.controller;

import org.jetbrains.annotations.NotNull;
import org.saphka.finance.exception.BaseException;
import org.saphka.finance.model.ErrorCode;
import org.saphka.finance.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        return handlerInternal(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleBaseException(Exception ex) {
        return handlerInternal(ErrorCode.INTERNAL, ex.getMessage());
    }

    @NotNull
    private ResponseEntity<ErrorResponse> handlerInternal(ErrorCode code, String message) {
        return ResponseEntity.status(code.getStatus()).body(new ErrorResponse(code.name(), message));
    }
}
