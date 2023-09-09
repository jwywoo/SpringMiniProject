package com.example.postcommentauth.board.service;

import com.example.postcommentauth.board.dto.BoardRequestDto;
import com.example.postcommentauth.board.dto.BoardResponseDto;
import com.example.postcommentauth.board.entity.Board;
import com.example.postcommentauth.board.repository.BoardRepository;
import com.example.postcommentauth.common.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    public BoardResponseDto boardCreate(BoardRequestDto requestDto, HttpServletRequest req) {
        Claims userinfo = userInfo(req);
        if (userinfo.getSubject() == null) {
            throw new NullPointerException("회원 정보가 존재하지 않습니다.");
        }
        Board newBoard = new Board(requestDto, userinfo.getSubject());
        return new BoardResponseDto(boardRepository.save(newBoard));
    }

    private Claims userInfo(HttpServletRequest req) {
        String givenToken = jwtUtil.getTokenFromRequest(req);
        givenToken = jwtUtil.substringToken(givenToken);
        if (!jwtUtil.validateToken(givenToken)) throw new IllegalArgumentException("유효하지 않은 회원 정보입니다");
        return jwtUtil.getUserInfoFromToken(givenToken);
    }
}
