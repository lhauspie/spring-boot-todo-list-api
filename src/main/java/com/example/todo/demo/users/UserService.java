package com.example.todo.demo.users;

import java.util.UUID;

public interface UserService {
    public UUID addUser(User user);
    public java.util.List<User> getUsers();
    public void updateUser(User user, UUID id);

}
