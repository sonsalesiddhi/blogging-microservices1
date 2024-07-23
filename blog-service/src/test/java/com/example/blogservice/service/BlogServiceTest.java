package com.example.blogservice.service;

import com.example.blogservice.document.Blog;
import com.example.blogservice.externel.UserService;
import com.example.blogservice.externel.model.UserResponse;
import com.example.blogservice.model.BlogRequest;
import com.example.blogservice.model.BlogResponse;
import com.example.blogservice.repository.BlogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private UserService userService;

    @Mock
    private BlogServiceUtils blogServiceUtils;

    @InjectMocks
    private BlogService blogService;

    @Test
    void getBlogById() {
        String blogId ="zxerrkgmimig";

        Blog blog  = Blog.builder()
                .id(blogId)
                .userId(1)
                .updateAt(Instant.now())
                .createdAt(Instant.now())
                .content("nice blog")
                .title("blog 1")
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .email("sid@gmail.com")
                .userName("siddhi")
                .password("asdf@11")
                .build();
        BlogResponse expectedBlogResponse = BlogResponse.builder()
                .id(blog.getId())
                .createdAt(blog.getCreatedAt())
                .updateAt(blog.getUpdateAt())
                .content(blog.getContent())
                .title(blog.getTitle())
                .email(userResponse.getEmail())
                .userName(userResponse.getUserName())
                .userId(userResponse.getId())
                .build();

        Mockito.when(userService.getUserById(1)).thenReturn(userResponse);
        Mockito.when(blogRepository.findById(blogId)).thenReturn(Optional.of(blog));

        BlogResponse actualBlogResponse = blogService.getBlogById(blogId);

        Assertions.assertEquals(expectedBlogResponse.getTitle(),actualBlogResponse.getTitle());
    }

    @Test
    void createBlog() {
        BlogRequest blogRequest = BlogRequest.builder()
                .userId(1)
                .content("mnbv")
                .title("blog2")
                .build();

        Instant time = Instant.now();

        Mockito.when(blogServiceUtils.getUUID()).thenReturn("stringId");
        Mockito.when(blogServiceUtils.getCreatedDate()).thenReturn(time);
        Mockito.when(blogServiceUtils.getUpdatedDate()).thenReturn(time);

        Blog blog  = Blog.builder()
                .id(blogServiceUtils.getUUID())
                .userId(blogRequest.getUserId())
                .updateAt(blogServiceUtils.getUpdatedDate())
                .createdAt(blogServiceUtils.getCreatedDate())
                .content(blogRequest.getContent())
                .title(blogRequest.getTitle())
                .build();


        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .email("sid@gmail.com")
                .userName("siddhi")
                .password("asdf@11")
                .build();

        BlogResponse expectedBlogResponse = BlogResponse.builder()
                .id(blog.getId())
                .createdAt(blog.getCreatedAt())
                .updateAt(blog.getUpdateAt())
                .content(blog.getContent())
                .title(blog.getTitle())
                .email(userResponse.getEmail())
                .userName(userResponse.getUserName())
                .userId(userResponse.getId())
                .build();

        Mockito.when(blogRepository.save(blog)).thenReturn(blog);
        Mockito.when(userService.getUserById(blogRequest.getUserId())).thenReturn(userResponse);

        BlogResponse actualBlogResponse= blogService.createBlog(blogRequest);

        Assertions.assertEquals(expectedBlogResponse,actualBlogResponse);

    }


    @Test
    void getBlogByUserId() {
        Integer userId = 1;

        List<Blog> blogs = new ArrayList<>();

        Instant time = Instant.now();

        Mockito.when(blogServiceUtils.getUUID()).thenReturn("stringId");
        Mockito.when(blogServiceUtils.getCreatedDate()).thenReturn(time);
        Mockito.when(blogServiceUtils.getUpdatedDate()).thenReturn(time);

        Blog blog  = Blog.builder()
                .id(blogServiceUtils.getUUID())
                .userId(1)
                .updateAt(blogServiceUtils.getUpdatedDate())
                .createdAt(blogServiceUtils.getCreatedDate())
                .content("nice blog")
                .title("blog 1")
                .build();
        blogs.add(blog);
        Mockito.when(blogRepository.findAllByUserId(userId)).thenReturn(blogs);

        List<BlogResponse> expectedBlogResponseList = new ArrayList<>();

        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .email("sid@gmail.com")
                .userName("siddhi")
                .password("asdf@11")
                .build();
        Mockito.when(userService.getUserById(blog.getUserId())).thenReturn(userResponse);

        BlogResponse expectedBlogResponse = BlogResponse.builder()
                .id(blog.getId())
                .createdAt(blog.getCreatedAt())
                .updateAt(blog.getUpdateAt())
                .content(blog.getContent())
                .title(blog.getTitle())
                .email(userResponse.getEmail())
                .userName(userResponse.getUserName())
                .userId(userResponse.getId())
                .build();

        expectedBlogResponseList.add(expectedBlogResponse);

        List<BlogResponse> actualBlogResponseList = blogService.getBlogByUserId(userId);

        Assertions.assertEquals(expectedBlogResponseList,actualBlogResponseList);

    }

    @Test
    void getAllBlogs() {

        List<Blog> blogs = new ArrayList<>();

        Instant time = Instant.now();

        Mockito.when(blogServiceUtils.getUUID()).thenReturn("stringId");
        Mockito.when(blogServiceUtils.getCreatedDate()).thenReturn(time);
        Mockito.when(blogServiceUtils.getUpdatedDate()).thenReturn(time);

        Blog blog  = Blog.builder()
                .id(blogServiceUtils.getUUID())
                .userId(1)
                .updateAt(blogServiceUtils.getUpdatedDate())
                .createdAt(blogServiceUtils.getCreatedDate())
                .content("nice blog")
                .title("blog 1")
                .build();
        blogs.add(blog);

        Mockito.when(blogRepository.findAll()).thenReturn(blogs);

        List<BlogResponse> expectedBlogResponseList = new ArrayList<>();

        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .email("sid@gmail.com")
                .userName("siddhi")
                .password("asdf@11")
                .build();
        Mockito.when(userService.getUserById(blog.getUserId())).thenReturn(userResponse);

        BlogResponse expectedBlogResponse = BlogResponse.builder()
                .id(blog.getId())
                .createdAt(blog.getCreatedAt())
                .updateAt(blog.getUpdateAt())
                .content(blog.getContent())
                .title(blog.getTitle())
                .email(userResponse.getEmail())
                .userName(userResponse.getUserName())
                .userId(userResponse.getId())
                .build();

        expectedBlogResponseList.add(expectedBlogResponse);

        List<BlogResponse> actualBlogResponseList = blogService.getAllBlogs();

        Assertions.assertEquals(expectedBlogResponseList,actualBlogResponseList);

    }
}