/*
package com.example.todo.demo.users;

import com.example.todo.demo.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@Tag (name = "user")
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@RequestBody UserDTO user ){
        UUID uuid = service.addUser(user);
        return ResponseEntity.created(URI.create("/users/" + uuid)).build();
    }

    @Operation(summary = "Find Users", description = "Search All the Users" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    })
    @GetMapping(value ="/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }


    @PutMapping(value = "/users/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateUser(@RequestBody UserDTO user, @PathVariable UUID id){
        service.updateUser(user, id);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @DeleteMapping (value = "/users/{id}")
    @Operation(summary = "Delete User", description = "Delete User by UUID" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    , @ApiResponse(responseCode = "404", description = "UUID not found",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))})

    public ResponseEntity deleteUser(@Parameter(description="User UUID to delete. Cannot be null or empty.",
            required=true, schema=@Schema(implementation = UUID.class)) @PathVariable UUID id) throws ResourceNotFoundException {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
*/
