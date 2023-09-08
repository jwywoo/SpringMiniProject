package com.example.postcommentauth.member.dto;

import lombok.Getter;

@Getter
public class StringResponseDto {
    private final String message;
    private final String status;
    public StringResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
