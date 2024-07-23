package com.example.commentservice.external;

import com.example.commentservice.external.model.BlogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BlogService {

    @Autowired
    private RestTemplate restTemplate;

    public BlogResponse getBlogById(String blogId){
        String uri = "http://localhost:8402/api/blog/"+blogId;
        try{
            BlogResponse blogResponse = restTemplate.getForObject(uri,BlogResponse.class);
            return blogResponse;
        }catch(Exception e){
            return null;
        }
    }
}
