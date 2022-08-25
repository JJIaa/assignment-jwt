package com.sparta.assignmentjwt.dto;

import com.sparta.assignmentjwt.entity.Post;
import com.sparta.assignmentjwt.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class CommentDTO {
    private String comment;
    private Post post;
    private User user;
}
