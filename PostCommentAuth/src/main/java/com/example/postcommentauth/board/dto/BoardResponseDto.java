package com.example.postcommentauth.board.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BoardResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
