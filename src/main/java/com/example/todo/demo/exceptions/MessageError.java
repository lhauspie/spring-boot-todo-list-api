package com.example.todo.demo.exceptions;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;

@Data
@Accessors(chain = true)
public class MessageError {
     Date timestamp = Date.from(Instant.now()); //": "2020-02-25T09:25:10.361+0000",
     HttpStatus status; //": 404,
     String error; //": "Not Found",
     String message; //": "Ressource not found !",
     String path; //: "/users/fc17a449-196d-4ebd-87fe-33d75e0764f5"
}
