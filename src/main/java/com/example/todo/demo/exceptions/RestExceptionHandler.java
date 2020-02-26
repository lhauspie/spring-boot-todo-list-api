package com.example.todo.demo.exceptions;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerRequest;


@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity handleResourceNotFound(ResourceNotFoundException e){
        String message = e.getMessage();

        MessageError messageError = new MessageError()
                                    .setStatus(HttpStatus.NOT_FOUND)
                                    .setMessage(e.getMessage())
                                    .setError("Resource Not Found");
//                                    .setPath(httpServletRequest.getURI().getPath());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
    }
}
