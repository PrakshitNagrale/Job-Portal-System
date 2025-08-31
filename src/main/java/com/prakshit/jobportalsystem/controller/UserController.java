package com.prakshit.jobportalsystem.controller;

import com.prakshit.jobportalsystem.dto.UserRequestDTO;
import com.prakshit.jobportalsystem.dto.UserResponseDTO;
import com.prakshit.jobportalsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) { //constructor injection
        this.userService = userService;
    }

    @PostMapping   //to create user in db
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){

       UserResponseDTO userResponse =  userService.createUser(userRequestDTO);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") //get user by id
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") UUID id){

       UserResponseDTO user = userService.getUserById(id);

       return  ResponseEntity.ok(user);
    }


    // to find all the users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){

        List<UserResponseDTO> userList = userService.getAllUser();

        return ResponseEntity.ok(userList);
    }

    //to update the user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") UUID id,@Valid @RequestBody UserRequestDTO userRequestDTO){

       UserResponseDTO updatedUser =  userService.updateUser(id,userRequestDTO);

       return  ResponseEntity.ok(updatedUser);
    }


    //to delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id){

        userService.deleteUser(id);

        return ResponseEntity.ok("User Deleted Successfully!");
    }

    //to get user by email
    @GetMapping("/emailId")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("emailId") String email){

       UserResponseDTO user =  userService.getUserByEmail(email);

       return ResponseEntity.ok(user);
    }
}
