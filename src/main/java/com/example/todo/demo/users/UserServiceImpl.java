package com.example.todo.demo.users;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	List<User> userList = new ArrayList<>();

	@Override
	public UUID addUser(User user) {
		sayCoucou();
		user.setId(UUID.randomUUID());
		userList.add(user);
		return user.getId();
	}

	@Override
	public List<User> getUsers() {
		return this.userList;
	}

	@Override
	public void updateUser(User user, UUID id) {
		sayCoucou();
		userList.stream()
				.filter(u -> u.getId().equals(id))
				.findFirst()
				.ifPresentOrElse(user1 -> {
							user1.setName(user.getName());
							user1.setFirstname(user.getFirstname());
						},
						() -> {
							user.setId(id);
							userList.add(user);
						});

	}

	private void sayCoucou() {
		System.out.println("COUCOU");
	}
}
