package com.sparta.assignmentjwt.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
@Service
@RequiredArgsConstructor
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
