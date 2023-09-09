package com.example.postcommentauth.board.dto;

import com.example.postcommentauth.board.entity.Board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BoardResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.content = board.getContent();
    }
}
