package com.example.blogservice.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

@Getter
@Setter
public class LikeEvent extends ApplicationEvent {
    private String blogId;
    private Integer userId;


    public LikeEvent(String blogId, Integer userId) {
        super(blogId);
        this.blogId = blogId;
        this.userId = userId;
    }

}