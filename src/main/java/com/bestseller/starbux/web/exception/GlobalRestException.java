package com.bestseller.starbux.web.exception;

import com.bestseller.starbux.exception.AlreadyExistException;
import com.bestseller.starbux.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalRestException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> alreadyExistHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

}
