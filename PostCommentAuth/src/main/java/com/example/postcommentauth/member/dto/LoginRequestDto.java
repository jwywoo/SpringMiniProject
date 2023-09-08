package com.example.postcommentauth.member.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginRequestDto {
    String username;
    String password;
}
