package com.example.todo.demo.tasks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {
	private UUID id;
	private String label;
}
