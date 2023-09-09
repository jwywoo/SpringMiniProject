package com.example.postcommentauth.board.controller;

import com.example.postcommentauth.board.dto.BoardRequestDto;
import com.example.postcommentauth.board.dto.BoardResponseDto;
import com.example.postcommentauth.board.service.BoardService;
import com.example.postcommentauth.common.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // Board Create
    @PostMapping("/board")
    public ResponseEntity<BoardResponseDto> boardCreate(@RequestBody BoardRequestDto requestDto, HttpServletRequest req) {
        return ResponseEntity.ok(boardService.boardCreate(requestDto, req));
    }
    // Board Read
    // List
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> boardList() {
        return ResponseEntity.ok(boardService.boardList());
    }
    // Detail
    @GetMapping("/board/{ID}")
    public ResponseEntity<BoardResponseDto> boardDetail(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.boardDetail(id));
    }
    // Board Update
    // Board Delete


}
