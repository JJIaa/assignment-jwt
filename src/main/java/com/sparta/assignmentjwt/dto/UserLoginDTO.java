package com.sparta.assignmentjwt.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;
}
