package com.example.postcommentauth.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long boardId;
    private String content;
}
