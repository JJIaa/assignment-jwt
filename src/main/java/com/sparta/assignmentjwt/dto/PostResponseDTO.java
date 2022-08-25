package com.sparta.assignmentjwt.dto;

import com.sparta.assignmentjwt.entity.Post;
import com.sparta.assignmentjwt.entity.Timestamped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@RequiredArgsConstructor
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
@Getter
public class PostResponseDTO extends Timestamped {

    private final String title;
    private final String username;

    public PostResponseDTO(Post post) {
        super.createdAt = post.getCreatedAt();
        super.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.username = post.getUsername();
    }

}