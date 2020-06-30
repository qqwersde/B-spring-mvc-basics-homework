package com.thoughtworks.capacity.gtb.mvc.Exception;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(400,message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(UserExistingException.class)
    public ResponseEntity<ErrorResult> handle(UserExistingException ex){
        ErrorResult errorResult = new ErrorResult(400,ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(NoAuthorizedException.class)
    public ResponseEntity<ErrorResult> handle(NoAuthorizedException ex){
        ErrorResult errorResult = new ErrorResult(400,ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
