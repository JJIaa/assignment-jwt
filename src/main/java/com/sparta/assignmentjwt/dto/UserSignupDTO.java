package com.sparta.assignmentjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserSignupDTO {
    private String username;
    private String password;
    private String passwordConfirm;
}
