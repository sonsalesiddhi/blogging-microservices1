package com.example.blogservice.service;

import com.example.blogservice.document.Blog;
import com.example.blogservice.event.LikeEvent;
import com.example.blogservice.exception.UserNotFoundException;
import com.example.blogservice.externel.UserService;
import com.example.blogservice.externel.model.LikeRequest;
import com.example.blogservice.externel.model.UserResponse;
import com.example.blogservice.model.BlogRequest;
import com.example.blogservice.model.BlogResponse;
import com.example.blogservice.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogServiceUtils blogServiceUtils;

    @Autowired
    private KafkaTemplate<String, LikeEvent> kafkaTemplate;

    public BlogResponse createBlog(BlogRequest blogRequest) {
        UserResponse userResponse =userService.getUserById(blogRequest.getUserId());
        String uuid = blogServiceUtils.getUUID();
        Instant createdDate = blogServiceUtils.getCreatedDate();
        Instant updatedDate = blogServiceUtils.getUpdatedDate();

        if(Objects.isNull(userResponse)) {
            throw new UserNotFoundException("User with id "+blogRequest.getUserId()+"Not Found");
        } else {
            Blog blog  = Blog.builder()
                    .id(uuid)
                    .userId(blogRequest.getUserId())
                    .title(blogRequest.getTitle())
                    .content(blogRequest.getContent())
                    .createdAt(createdDate)
                    .updateAt(updatedDate)
                    .build();
            Blog savedBlog = blogRepository.save(blog);
            BlogResponse blogResponse = BlogResponse.builder()
                    .id(savedBlog.getId())
                    .userId(savedBlog.getUserId())
                    .userName(userResponse.getUserName())
                    .email(userResponse.getEmail())
                    .title(savedBlog.getTitle())
                    .content(savedBlog.getContent())
                    .createdAt(savedBlog.getCreatedAt())
                    .updateAt(savedBlog.getUpdateAt())
                    .build();
            return blogResponse;
        }

    }

    public BlogResponse getBlogById( String id) {
        Blog blog = blogRepository.findById(id).get();
        UserResponse userResponse =userService.getUserById(blog.getUserId());

        BlogResponse blogResponse = BlogResponse.builder()
                .id(blog.getId())
                .userId(blog.getUserId())
                .userName(userResponse.getUserName())
                .email(userResponse.getEmail())
                .title(blog.getTitle())
                .content(blog.getContent())
                .createdAt(blog.getCreatedAt())
                .updateAt(blog.getUpdateAt())
                .build();

        return blogResponse;

    }

    public List<BlogResponse> getBlogByUserId(Integer userId) {
        List<Blog> blogs =blogRepository.findAllByUserId(userId);

        List<BlogResponse> blogResponseList = new ArrayList<>();


        for(Blog blog :blogs){
            UserResponse userResponse =userService.getUserById(blog.getUserId());

            BlogResponse blogResponse = BlogResponse.builder()
                    .id(blog.getId())
                    .userId(blog.getUserId())
                    .userName(userResponse.getUserName())
                    .email(userResponse.getEmail())
                    .title(blog.getTitle())
                    .content(blog.getContent())
                    .createdAt(blog.getCreatedAt())
                    .updateAt(blog.getUpdateAt())
                    .build();

            blogResponseList.add(blogResponse);
        }
        return blogResponseList;
    }

    public List<BlogResponse> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();

        List<BlogResponse> blogResponseList = new ArrayList<>();


        for(Blog blog :blogs){
            UserResponse userResponse =userService.getUserById(blog.getUserId());

            BlogResponse blogResponse = BlogResponse.builder()
                    .id(blog.getId())
                    .userId(blog.getUserId())
                    .userName(userResponse.getUserName())
                    .email(userResponse.getEmail())
                    .title(blog.getTitle())
                    .content(blog.getContent())
                    .createdAt(blog.getCreatedAt())
                    .updateAt(blog.getUpdateAt())
                    .build();

            blogResponseList.add(blogResponse);
        }
        return blogResponseList;
    }


    public String likeBlog(LikeRequest likeRequest) {
        kafkaTemplate.send("topic",new LikeEvent(likeRequest.getBlogId(), likeRequest.getUserId()));
        return "Like done";
    }
}
