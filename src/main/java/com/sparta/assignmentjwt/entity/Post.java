package com.sparta.assignmentjwt.entity;

import com.sparta.assignmentjwt.dto.PostRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    //하나의 유저가 여러 게시글을 작성할 수 있음, 즉시 로딩
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    //하나의 게시글이 여러 코멘트를 가질 수 있음, 즉시 로딩
    //게시글이 삭제되면 거기에 달린 댓글도 삭제
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDTO requestDTO, User user) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.username = requestDTO.getUser().getUsername();
        this.commentList = requestDTO.getCommentList();
        this.user = user;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
}
