package com.example.userapp.exception;
import org.springframework.web.bind.annotation.*; import org.springframework.http.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<String> handle(ResourceNotFoundException ex){
   return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
 }
}