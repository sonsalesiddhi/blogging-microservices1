package com.example.userservice.controller;

import com.example.userservice.model.UserRequest;
import com.example.userservice.model.UserResponse;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/users")

    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
