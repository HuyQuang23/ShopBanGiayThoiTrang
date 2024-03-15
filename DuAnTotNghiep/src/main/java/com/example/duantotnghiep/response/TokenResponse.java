package com.example.duantotnghiep.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

    private String accessToken;

    private String token;

    private String message;

    private String role;

    private String username;
}