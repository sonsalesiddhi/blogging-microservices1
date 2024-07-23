package com.example.commentservice.external;

import com.example.commentservice.external.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    public UserResponse getUserById(Integer userId){
        String uri = "http://localhost:8401/api/user/"+userId;

        try{
            UserResponse userResponse = restTemplate.getForObject(uri, UserResponse.class);
            return userResponse;
        }catch(Exception e){
            return null;
        }
    }
}
