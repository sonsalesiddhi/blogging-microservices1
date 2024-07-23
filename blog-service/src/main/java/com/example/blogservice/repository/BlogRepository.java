package com.example.blogservice.repository;

import com.example.blogservice.document.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findAllByUserId(Integer userId);
}
