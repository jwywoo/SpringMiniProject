package com.example.postcommentauth.comment.controller;

import com.example.postcommentauth.comment.dto.CommentRequestDto;
import com.example.postcommentauth.comment.dto.CommentResponseDto;
import com.example.postcommentauth.comment.service.CommentService;
import com.example.postcommentauth.common.dto.StringResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // Create
    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDto> commentCreate(@RequestBody CommentRequestDto requestDto, HttpServletRequest req) {
        return ResponseEntity.ok(commentService.commentCreate(requestDto, req));
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDto> commentUpdate(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest req) {
        return ResponseEntity.ok(commentService.commentUpdate(id, requestDto, req));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<StringResponseDto> commentDelete(@PathVariable Long id, HttpServletRequest req) {
        return ResponseEntity.ok(commentService.commentDelete(id, req));
    }
}
