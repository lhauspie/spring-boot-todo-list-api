package com.example.todo.demo.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;
    private String name;
    private String firstname;

    public UserDTO(String name, String firstname){
        this.name = name;
        this.firstname = firstname;
    }

}
