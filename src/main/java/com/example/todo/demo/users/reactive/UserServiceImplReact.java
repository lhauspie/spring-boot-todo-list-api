package com.example.todo.demo.users.reactive;

import com.example.todo.demo.exceptions.ResourceNotFoundException;
import com.example.todo.demo.users.User;
import com.example.todo.demo.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplReact implements UserService {

    private UserReactRepository userRepo;

    @Autowired
    public UserServiceImplReact(UserReactRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Mono<UUID> addUser(UserDTO user) {

        return userRepo
                .save(
                        User.builder()
                                .firstname(user.getFirstname())
                                .name(user.getName())
                                .id(UUID.randomUUID())
                                .build()
                )
                .map(user1 -> user1.getId());

    }

    @Override
    public Flux<UserDTO> getUsers() {
        return userRepo
                .findAll()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getFirstname()));
    }

    @Override
    public Mono<Void> updateUser(UserDTO user, UUID id) {
        return userRepo.save(new User(id, user.getName(), user.getFirstname())).then();
    }

    @Override
    public Mono<Void> deleteUser(UUID uuid) {
        System.out.println("toto");
        return userRepo.findById(uuid)
                .flatMap(user ->
                             userRepo.delete(user)
                                    .then(Mono.just(user))
                )
                .switchIfEmpty(Mono.defer(() -> {
                    return Mono.error(new ResourceNotFoundException("Ressource not found !"));
                }))
                .then()
                .log();
        }


}

