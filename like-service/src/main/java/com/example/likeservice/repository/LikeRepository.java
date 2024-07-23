package com.example.likeservice.repository;

import com.example.likeservice.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Integer> {

    List<Like> findAllByBlogId(String blogId);
}
