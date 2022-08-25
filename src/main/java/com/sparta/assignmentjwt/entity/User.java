package com.sparta.assignmentjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.assignmentjwt.dto.UserLoginDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;




    @Column(nullable = false)
    @JsonIgnore
    @Transient
    private String passwordConfirm;



    public User(UserLoginDTO loginDTO) {
        this.username = loginDTO.getUsername();
        this.password = loginDTO.getPassword();
    }

    //여러개의 게시글이 하나의 유저를 가질 수 있음, 지연 로딩
    //유저 삭제시 해당 유저가 작성한 게시글 모두 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Post> postList;

    //여러개의 댓글이 하나의 유저를 가지 수 있음, 지연 로딩
    //유저 삭제시 해당 유저가 작성한 댓글 모두 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> commentList;


}
