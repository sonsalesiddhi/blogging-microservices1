package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.model.UserRequest;
import com.example.userservice.model.UserResponse;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User user = User.builder()

                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .build();

        return userResponse;
    }

    public UserResponse getUserById(Integer userId) {
           User user = userRepository.findById(userId).get();
           UserResponse userResponse= UserResponse.builder()
                   .id(userId)
                   .userName(user.getUserName())
                   .password(user.getPassword())
                   .email(user.getEmail())
                   .build();

           return userResponse;
    }

    public List<UserResponse> getAllUsers() {
        List<User> user = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        for(User user1:user){
            UserResponse userResponse = UserResponse.builder()
                    .id(user1.getId())
                    .userName(user1.getUserName())
                    .email(user1.getEmail())
                    .password(user1.getPassword())
                    .build();

            userResponseList.add(userResponse);
        }

        return userResponseList;
    }
}
