package com.example.todo.demo.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String firstname;

    public User(String name, String firstname){
        this.name = name;
        this.firstname = firstname;
    }

}
