package com.example.todo.demo.users.reactive;

import com.example.todo.demo.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class UserControllerReact {


    UserService service;

    @Autowired
    public UserControllerReact(UserService service) {
        this.service = service;
    }


    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity> createUser(@RequestBody UserDTO user ){
        Mono<UUID> uuid = service.addUser(user);

        return uuid.map(uuid1 -> {
            return ResponseEntity.created(URI.create("/users/" + uuid1)).build();
        });

    }


    @GetMapping(value ="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<UserDTO> getUsers(){
        //TODO: ResponseEntity.ok();
        return service.getUsers();
    }


    @PutMapping(value = "/users/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> updateUser(@RequestBody UserDTO user, @PathVariable UUID id){
        return service.updateUser(user, id);
        }


    @DeleteMapping (value = "/users/{id}")
    public Mono<ResponseEntity> deleteUser(@PathVariable UUID id)  {
        //TODO: throws ResourceNotFoundException
        return service.deleteUser(id).map(aVoid -> ResponseEntity.noContent().build());
    }

}
