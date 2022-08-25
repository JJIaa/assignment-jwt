package com.sparta.assignmentjwt.dto;

import com.sparta.assignmentjwt.entity.Comment;
import com.sparta.assignmentjwt.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
    private User user;
    private List<Comment> commentList;
}
