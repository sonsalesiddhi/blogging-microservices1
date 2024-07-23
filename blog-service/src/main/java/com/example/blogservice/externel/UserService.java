package com.example.blogservice.externel;

import com.example.blogservice.externel.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    // http://localhost:8401/api/user?id=1

    @Autowired
    private RestTemplate restTemplate;

    public UserResponse getUserById(Integer userId){
        String url = "http://localhost:8401/api/user/"+userId;
        try{
            UserResponse userResponse = restTemplate.getForObject(url, UserResponse.class);
            return userResponse;
        } catch (Exception e) {
            return null;
        }

    }
}
