package com.example.todo.demo.tasks;

import com.example.todo.demo.users.User;
import com.example.todo.demo.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity> createUser(@RequestBody User user ){
        return service.addUser(user)
            .map(
                id -> ResponseEntity.created(URI.create("/users/" + id)).build()
            );
    }

    @GetMapping(value ="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<User> getUsers(){
        return service.getUsers();
    }

    @PutMapping(value = "/users/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable UUID id){
        service.updateUser(user, id);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @DeleteMapping (value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id)  {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
