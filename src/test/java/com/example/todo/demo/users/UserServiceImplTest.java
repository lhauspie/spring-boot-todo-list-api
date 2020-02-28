/*
package com.example.todo.demo.users;


import com.example.todo.demo.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest {

    UserService service;

    @Mock
    UserRepository repository; //= Mockito.mock(UserRepository.class)

    @Captor
    ArgumentCaptor<User> argumentCaptor;

    @BeforeEach
    public void load(){
        //MockitoAnnotations.initMocks(this);
        repository = Mockito.mock(UserRepository.class);
        service = new UserServiceImpl(repository);

        Mockito.when(repository.save(any())).thenReturn(User.builder()
                .firstname("FirstName")
                .name("LastName")
                .id(UUID.randomUUID()).build());

        argumentCaptor = ArgumentCaptor.forClass(User.class);
    }

    @Test
    public void updateUser(){
        //Given si j'ai un utilisateur dans la base
        //When quand je mets à jour ses propriétés
        //Then les propriétés devraient être mises à jour


        UUID id = UUID.randomUUID();
        service.updateUser(UserDTO.builder()
                .firstname("FirstName")
                .name("LastName")
                .id(id).build(), id);

        Mockito.verify(repository).save(argumentCaptor.capture());
        User value = argumentCaptor.getValue();

        Assertions.assertEquals("FirstName", value.getFirstname());
        Assertions.assertEquals("LastName", value.getName());
        Assertions.assertEquals(id, value.getId());

    }

    @Test
    public void getAllUser() {
        //Given j'ai 2 entités dans la base
        Mockito.when(repository.findAll()).thenReturn(
                Arrays.asList(
                        User.builder()
                                .firstname("FirstName")
                                .name("LastName")
                                .id(UUID.randomUUID()).build(),
                        User.builder()
                                .firstname("FirstName")
                                .name("LastName")
                                .id(UUID.randomUUID()).build()));

        //When j'appelle service.getUsers()
        List<UserDTO> users = service.getUsers();

        //Then Ca me renvoie une Liste avec 2 éléments
        Assert.assertTrue(users.size()==2);
    }

    @Test
    public void deleteUser(){
        //Given je recherche un user id = UUID,
        UUID id = UUID.randomUUID();
        Mockito.when(repository.findById(id))
                .thenReturn(Optional.of(User.builder()
                                .firstname("FirstName")
                                .name("LastName")
                                .id(id).build()));

        //When je tente de le supprimer
        service.deleteUser(id);
        //Then il est supprimé
        Mockito.verify(repository).delete(argumentCaptor.capture());
        User value = argumentCaptor.getValue();

        Assertions.assertEquals("FirstName", value.getFirstname());
        Assertions.assertEquals("LastName", value.getName());
    }

    @Test
    public void deleteUnknownUser(){
        //Given je recherche un user id = UUID,
        UUID id = UUID.randomUUID();
        Mockito.when(repository.findById(id))
                .thenReturn(Optional.of(User.builder()
                        .firstname("FirstName")
                        .name("LastName")
                        .id(id).build()));

        //When je tente de le supprimer
        UUID uuid = UUID.randomUUID();
        try {
            service.deleteUser(uuid);
            Assert.fail("Should throw an Exception");
        }
        catch (ResourceNotFoundException e)
        {
            Mockito.verify(repository, Mockito.times(0)).delete(any());
        }

        //Then il est supprimé
        */
/*
        User value = argumentCaptor.getValue();
        *//*


        */
/*
        Assertions.assertEquals("FirstName", value.getFirstname());
        Assertions.assertEquals("LastName", value.getName());
         *//*


    }
}
*/
