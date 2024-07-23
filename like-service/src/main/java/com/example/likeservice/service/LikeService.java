package com.example.likeservice.service;

import com.example.likeservice.entity.Like;
import com.example.likeservice.model.LikeRequest;
import com.example.likeservice.model.LikeResponse;
import com.example.likeservice.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public LikeResponse likeBlog(LikeRequest likeRequest) {
        Like like1 = Like.builder()
                .blogId(likeRequest.getBlogId())
                .userId(likeRequest.getUserId())
                .build();

        Like savedLike = likeRepository.save(like1);
        LikeResponse likeResponse = LikeResponse.builder()
                .id(savedLike.getId())
                .blogId(savedLike.getBlogId())
                .userId(savedLike.getUserId())
                .build();

        return likeResponse;

    }

    public Integer getLikeCount(String blogId) {
       List<Like> likes = likeRepository.findAllByBlogId(blogId);
         return likes.size() ;
    }
}
