package com.example.commentservice.controller.repository;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.model.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByBlogId(String blogId);

    List<Comment> findAllByUserId(Integer userId);
}
