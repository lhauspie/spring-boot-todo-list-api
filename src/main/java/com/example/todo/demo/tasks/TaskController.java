package com.example.todo.demo.tasks;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.Collectors;

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
	@ResponseStatus(value = HttpStatus.CREATED, reason = "")
	public ResponseEntity createTask(@RequestBody TaskDTO task) {
		return ResponseEntity.created(URI.create("/tasks/" + taskService.createTask(task.getLabel()))).build();
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getTasks() {
		return ResponseEntity.ok(taskService.getTasks().stream().map(task -> {
			TaskDTO taskDTO = new TaskDTO();
			taskDTO.setId(task.getId());
			taskDTO.setLabel(task.getLabel());
			return taskDTO;
		}).collect(Collectors.toList()));
	}
}
