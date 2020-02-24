package com.example.todo.demo.users;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;


public class UserServiceImplTest {

    UserService service;

    @BeforeEach
    public void load(){
        service = new UserServiceImpl();
    }

    @Test
    public void givenTwoUsersExist_whenUpdateFirstUser_thenUserUpdated(){
        UUID uuid = service.addUser(new User("toto", "titi"));
        service.addUser(new User("dan", "jon"));

        service.updateUser(new User("bernard", "champ"), uuid);
        service.getUsers()
                .stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst()
                .ifPresentOrElse(user -> {
                    Assertions.assertEquals("bernard", user.getName());
                    Assertions.assertEquals("champ", user.getFirstname());
                },
                        () -> {
                            Assert.fail("no user found for " + uuid);
                        });


    }
}
