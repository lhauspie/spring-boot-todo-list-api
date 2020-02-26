package com.example.todo.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@RequestBody UserDTO user ){
        UUID uuid = service.addUser(user);
        return ResponseEntity.created(URI.create("/users/" + uuid)).build();
    }

    @GetMapping(value ="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }

    @PutMapping(value = "/users/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateUser(@RequestBody UserDTO user, @PathVariable UUID id){
        service.updateUser(user, id);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @DeleteMapping (value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id)  {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
