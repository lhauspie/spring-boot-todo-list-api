package com.example.todo.demo.tasks;

import com.example.todo.demo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepo;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}

	@Override
	public Mono<UUID> createTask(String label) {
		TaskEntity t = new TaskEntity();
		t.setLabel(label);
		return taskRepo.save(t).map(TaskEntity::getId);
	}

	@Override
	public Flux<TaskEntity> getTasks() {
		return taskRepo.findAll()
				.doOnNext(e ->
						System.out.println("Executing on thread: " +
								Thread.currentThread().getName()
						)
				);
	}

	@Override
	public Mono<Void> deleteTask(UUID id) {
		return taskRepo.findById(id)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found")))
				.flatMap(taskRepo::delete)
				.then();
	}
}
