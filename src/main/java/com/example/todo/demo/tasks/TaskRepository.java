package com.example.todo.demo.tasks;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface TaskRepository extends ReactiveMongoRepository<TaskEntity, UUID> {
}
