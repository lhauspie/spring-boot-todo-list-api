package com.example.todo.demo.users;

import java.util.UUID;

public interface UserService {
    public UUID addUser(UserDTO user);
    public java.util.List<UserDTO> getUsers();
    public void updateUser(UserDTO user, UUID id);
    public void deleteUser(UUID uuid) ;

}
