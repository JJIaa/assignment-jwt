package com.sparta.assignmentjwt.entity;

import com.sparta.assignmentjwt.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;

    //하나의 게시글이 여러개의 댓글을 가질 수 있음, 즉시 로딩
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POST_ID")
    private Post post;

    //하나의 유저가 여러개의 댓글을 가질 수 있음, 즉시 로딩
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Comment(CommentDTO commentDTO, Post post, User user) {
        this.comment = commentDTO.getComment();
        this.username = commentDTO.getUser().getUsername();
        this.post = post;
        this.user = user;

    }
}
