package com.example.todo.demo.tasks;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {
}
