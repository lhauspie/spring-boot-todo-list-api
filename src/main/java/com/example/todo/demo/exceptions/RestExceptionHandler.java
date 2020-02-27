package com.example.todo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class RestExceptionHandler {

    //{ResourceNotFoundException.class}
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity handleResourceNotFound(javax.servlet.http.HttpServletRequest httpServletRequest, ResourceNotFoundException e){
        String message = e.getMessage();

        MessageError messageError = new MessageError()
                                    .setStatus(HttpStatus.NOT_FOUND)
                                    .setMessage(e.getMessage())
                                    .setError("Resource Not Found")
                                    .setPath(httpServletRequest.getServletPath());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
    }
}
