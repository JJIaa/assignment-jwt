package com.sparta.assignmentjwt.controller;

import com.sparta.assignmentjwt.dto.UserLoginDTO;
import com.sparta.assignmentjwt.dto.UserSignupDTO;
import com.sparta.assignmentjwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/members")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupDTO userSignupDTO) {
        return userService.register(userSignupDTO);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
}
