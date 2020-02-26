package com.example.todo.demo.tasks;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "Everything is OK, the task is created with provided body",
					headers = {
							@Header(name="Location", description = "The location of the created resource")
					}
			)
	})
	@ResponseStatus(
			value = HttpStatus.CREATED,
			reason = "Everything is OK, the task is created with provided body")
	public Mono<ResponseEntity> createTask(@RequestBody TaskDTO task) {
		return taskService.createTask(task.getLabel())
				.map(t -> ResponseEntity.created(URI.create("/tasks/" + t.toString())).build());
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<TaskDTO> getTasks() {
		return taskService.getTasks()
				.map(task ->
						new TaskDTO()
								.setId(task.getId())
								.setLabel(task.getLabel())
				);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity> deleteTask(@PathVariable("id") UUID id) {
		return taskService.deleteTask(id)
				.map(t -> ResponseEntity.noContent().build());
	}
}
