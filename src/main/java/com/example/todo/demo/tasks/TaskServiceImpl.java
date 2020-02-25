package com.example.todo.demo.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepo;

	@Override
	public UUID createTask(String label) {
		TaskEntity t = new TaskEntity();
		t.setLabel(label);
		t = taskRepo.save(t);
		return t.getId();
	}

	@Override
	public List<TaskEntity> getTasks() {
		List<TaskEntity> tasks = StreamSupport.stream(taskRepo.findAll().spliterator(), false)
				.collect(Collectors.toList());
//		if (tasks.size() > 0) {
//			TaskEntity taskEntity = tasks.get(0);
//			taskEntity.setLabel("UN LABEL");
//			taskRepo.save(taskEntity);
//		}
		return tasks;
	}
}
