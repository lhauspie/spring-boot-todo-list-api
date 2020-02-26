package com.example.todo.demo.users;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
public class UserEntity {

	@Id
	private UUID id;
	private String firstName;
	private String lastName;
}
