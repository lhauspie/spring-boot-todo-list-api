/*
package com.example.todo.demo.users;

import com.example.todo.demo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    List<UserDTO> userList = new ArrayList<>();
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UUID addUser(UserDTO user) {

        User save = userRepo.save(User.builder()
                .firstname(user.getFirstname())
                .name(user.getName())
                .id(UUID.randomUUID())
                .build());

        return save.getId();
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepo.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getFirstname()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(UserDTO user, UUID id) {
		sayCoucou();
    	userRepo.save(new User(id, user.getName(), user.getFirstname()));
    }

    @Override
    public void deleteUser(UUID uuid) {
		sayCoucou();
        Optional<User> byId = userRepo.findById(uuid);
        byId.map(user -> {
            userRepo.delete(user);
            return user;
        })
                .orElseThrow(() -> new ResourceNotFoundException("Ressource not found !"));

    }

	private void sayCoucou() {
		System.out.println("Coucou");
	}
}
*/
