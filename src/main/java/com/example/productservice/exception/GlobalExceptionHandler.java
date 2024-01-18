package com.example.productservice.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> ResourceNotFoundExceptionHandler(ResourceNotFoundException e, WebRequest w){
        ErrorDetails error=new ErrorDetails(new Date(),e.getMessage(),w.getDescription(false));
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(InvalidTitleException.class)
    public ResponseEntity<ErrorDetails> InvalidTitleExceptionHandler(InvalidTitleException e, WebRequest w){
        ErrorDetails error=new ErrorDetails(new Date(),e.getMessage(),w.getDescription(false));
        return ResponseEntity.ok(error);
    }
}
