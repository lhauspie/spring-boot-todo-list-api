package com.example.todo.demo.users;


import com.example.todo.demo.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest {

	UserService service;

	@Mock
	UserRepository repository; //= Mockito.mock(UserRepository.class)

	@Captor
	ArgumentCaptor<UserEntity> argumentCaptor;

	@BeforeEach
	public void load(){
		//MockitoAnnotations.initMocks(this);
		repository = Mockito.mock(UserRepository.class);
		service = new UserServiceImpl(repository);

		Mockito.when(repository.save(any())).thenReturn(Mono.just(UserEntity.builder()
				.firstName("FirstName")
				.lastName("LastName")
				.id(UUID.randomUUID()).build()));

		argumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
	}

	@Test
	public void updateUser(){
		//Given si j'ai un utilisateur dans la base
		//When quand je mets à jour ses propriétés
		//Then les propriétés devraient être mises à jour


		UUID id = UUID.randomUUID();
		service.updateUser(User.builder()
				.firstname("FirstName")
				.name("LastName")
				.id(id).build(), id);

		Mockito.verify(repository).save(argumentCaptor.capture());
		UserEntity value = argumentCaptor.getValue();

		Assertions.assertEquals("FirstName", value.getFirstName());
		Assertions.assertEquals("LastName", value.getLastName());
		Assertions.assertEquals(id, value.getId());

	}

	@Test
	public void getAllUser() {
		//Given j'ai 2 entités dans la base
		Mockito.when(repository.findAll()).thenReturn(
				Flux.just(
						UserEntity.builder()
								.firstName("FirstName")
								.lastName("LastName")
								.id(UUID.randomUUID()).build(),
						UserEntity.builder()
								.firstName("FirstName")
								.lastName("LastName")
								.id(UUID.randomUUID()).build()
				)
		);

		//When j'appelle service.getUsers()
		Flux<User> users = service.getUsers();

		//Then Ca me renvoie une Liste avec 2 éléments
		StepVerifier.create(users)
				.expectNextCount(2)
				.expectComplete();
	}

	@Test
	public void deleteUser(){
		//Given je recherche un user id = UUID,
		UUID id = UUID.randomUUID();
		Mockito.when(repository.findById(id))
				.thenReturn(Mono.just(UserEntity.builder()
						.firstName("FirstName")
						.lastName("LastName")
						.id(id).build()));

		Mockito.when(repository.delete(any()))
				.thenReturn(Mono.empty());

		//When je tente de le supprimer
		Mono<Void> deleteUser = service.deleteUser(id);

		//Then il est supprimé
		StepVerifier.create(deleteUser)
				.verifyComplete();

		Mockito.verify(repository).delete(argumentCaptor.capture());
		UserEntity value = argumentCaptor.getValue();

		Assertions.assertEquals("FirstName", value.getFirstName());
		Assertions.assertEquals("LastName", value.getLastName());
	}

	@Test
	public void deleteUnknownUser(){
		//Given je recherche un user id = UUID,
		UUID id = UUID.randomUUID();
		Mockito.when(repository.findById(id))
				.thenReturn(Mono.empty());

		//When je tente de le supprimer
		Mono<Void> deleteUser = service.deleteUser(id);

		//Then il est supprimé
		StepVerifier.create(deleteUser)
				.expectError(ResourceNotFoundException.class)
				.verify();

		Mockito.verify(repository, Mockito.times(0)).delete(any());
	}
}
