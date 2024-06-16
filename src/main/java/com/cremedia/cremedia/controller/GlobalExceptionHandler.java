//package com.cremedia.cremedia.controller;
//
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ExceptionDTO handleNotFound(EntityNotFoundException entityNotFoundException){
//        log.error("ActionLog.error not found: {} ", entityNotFoundException.getMessage());
//        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(), entityNotFoundException.getMessage());
//    }
//
////    @ExceptionHandler(EntityExistException.class)
////    @ResponseStatus(HttpStatus.CONFLICT)
////    public ExceptionDTO handleNotFound(EntityExistException entityExistException){
////        log.error("ActionLog.error not found: {} ", entityExistException.getMessage());
////        return new ExceptionDTO(HttpStatus.CONFLICT.value(), entityExistException.getMessage());
////    }
//
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//
////    @ExceptionHandler(Exception.class)
////    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
////    public ExceptionDTO handleGlobal(Exception e){
////        return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
////    }
////
////    @ExceptionHandler(AuthenticationException.class)
////    @ResponseStatus(HttpStatus.UNAUTHORIZED)
////    public ExceptionDTO handleAuthenticationException(AuthenticationException e){
////        log.error("AuthenticationException ->  {}", e.getMessage());
////        return new ExceptionDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
////    }
////
////    @ExceptionHandler(BadCredentialsException.class)
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    public ExceptionDTO handleBadCredentialsException(BadCredentialsException e){
////        log.error("BadCredentialsException -> {}", e.getMessage());
////        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage());
////    }
//
//
//
//
//}
