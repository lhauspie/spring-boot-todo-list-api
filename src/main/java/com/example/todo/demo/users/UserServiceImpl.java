package com.example.todo.demo.users;

import com.example.todo.demo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    List<User> userList = new ArrayList<>();
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public Mono<UUID> addUser(User user) {
        return userRepo
            .save(UserEntity.builder()
                .firstName(user.getFirstname())
                .lastName(user.getName())
                .id(UUID.randomUUID())
                .build())
            .map(e -> e.getId());
    }

    @Override
    public Flux<User> getUsers() {
        return userRepo.findAll()
                .map(user -> new User(user.getId(), user.getLastName(), user.getFirstName()));
    }

    @Override
    public Mono<Void> updateUser(User user, UUID id) {
    	return userRepo.save(
    	    UserEntity.builder()
              .id(id)
              .lastName(user.getName())
              .firstName(user.getFirstname())
              .build())
          .then();
    }

    @Override
    public Mono<Void> deleteUser(UUID id) {
        return userRepo.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Resource not found")))
            .flatMap(user -> {
                return userRepo.delete(user);
            })
            .then();
    }
}
