package com.example.todo.demo.users.reactive;

import com.example.todo.demo.users.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface UserReactRepository extends ReactiveMongoRepository<User, UUID> {
}
