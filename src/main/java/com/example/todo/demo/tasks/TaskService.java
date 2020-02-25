package com.example.todo.demo.tasks;

import java.util.List;
import java.util.UUID;

public interface TaskService {

	UUID createTask(String label);
	List<TaskEntity> getTasks();
}
