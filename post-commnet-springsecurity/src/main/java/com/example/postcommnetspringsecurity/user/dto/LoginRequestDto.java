package com.example.postcommnetspringsecurity.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
