package com.camp.educationalsite.utils.Error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Details> details = ex.getFieldErrors().stream().map((t)-> new Details(t.getField(),t.getDefaultMessage())).collect(Collectors.toList());
        
        ErrorModel eModel = new ErrorModel(HttpStatus.BAD_REQUEST,details);

        return new ResponseEntity<>(eModel,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorModel> handleValidationExceptions(HttpMessageNotReadableException ex) {
        Details d = new Details(ex.getCause().getMessage(),ex.getLocalizedMessage());
        List<Details> details = List.of(d);
        
        ErrorModel eModel = new ErrorModel(HttpStatus.BAD_REQUEST,details);

        return new ResponseEntity<>(eModel,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorModel> handleValidationExceptions(MissingServletRequestParameterException ex) {
        Details d = new Details("Missing Input",ex.getLocalizedMessage());
        List<Details> details = List.of(d);
        
        ErrorModel eModel = new ErrorModel(HttpStatus.BAD_REQUEST,details);

        return new ResponseEntity<>(eModel,HttpStatus.BAD_REQUEST);
    }
}