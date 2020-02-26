package com.example.todo.demo.tasks;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.UUID;

public class TaskServiceTests {

	@Mock
	private TaskRepository taskRepository;

	private TaskService taskService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

		taskService = new TaskServiceImpl(taskRepository);
	}

	@Test
	public void givenNoTasksInDB_whenTryToGetAll_thenNoTaskFound() throws InterruptedException {
		TestPublisher<TaskEntity> publisher = TestPublisher.create();

		Mockito.when(taskRepository.findAll()).thenReturn(publisher.flux());

		UUID id1 = UUID.randomUUID();
		UUID id2 = UUID.randomUUID();

		StepVerifier.create(taskRepository.findAll())
				.then(() -> publisher.emit(
						new TaskEntity().setId(id1),
						new TaskEntity().setId(id2)
					)
				)
				.expectNext(new TaskEntity().setId(id1))
				.expectNext(new TaskEntity().setId(id2))
				.verifyComplete();
	}
}
