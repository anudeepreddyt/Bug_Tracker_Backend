package com.anudeepreddy.Bug_Tracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GobalException {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponse> alreadyExistException(AlreadyExistException e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> commonException(CommonException e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(errorResponse);
    }
}
