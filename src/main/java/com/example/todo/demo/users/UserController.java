package com.example.todo.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@RequestBody User user ){
        UUID uuid = service.addUser(user);
        return ResponseEntity.created(URI.create("/users/" + uuid)).build();
    }

    @GetMapping(value ="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }

    @PutMapping(value = "/users/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable UUID id){
        service.updateUser(user, id);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

}
