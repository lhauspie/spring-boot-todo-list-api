package com.example.todo.demo.users;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    private UUID id;
    private String name;
    private String firstname;
}
