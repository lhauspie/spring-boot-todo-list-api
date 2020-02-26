package com.example.todo.demo.tasks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TaskService {

	Mono<UUID> createTask(String label);

	Flux<TaskEntity> getTasks();

	Mono<Void> deleteTask(UUID id);
}
