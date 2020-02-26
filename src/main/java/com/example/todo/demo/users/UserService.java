package com.example.todo.demo.users;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    public Mono<UUID> addUser(User user);
    public Flux<User> getUsers();
    public Mono<Void> updateUser(User user, UUID id);
    public Mono<Void> deleteUser(UUID uuid) ;

}
