package com.sparta.assignmentjwt.repository;

import com.sparta.assignmentjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


    //토큰..
    boolean existsByUsername(String username);
    User findByRefreshToken(String refreshToken);
}
