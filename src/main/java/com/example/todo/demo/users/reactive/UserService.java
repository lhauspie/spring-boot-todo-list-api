package com.example.todo.demo.users.reactive;

import com.example.todo.demo.users.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    public Mono<UUID> addUser(UserDTO user);
    public Flux<UserDTO> getUsers();
    public Mono<Void> updateUser(UserDTO user, UUID id);
    public Mono<Void> deleteUser(UUID uuid) ;

}
