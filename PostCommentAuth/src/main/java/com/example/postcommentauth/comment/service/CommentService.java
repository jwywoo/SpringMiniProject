package com.example.postcommentauth.comment.service;

import com.example.postcommentauth.comment.dto.CommentRequestDto;
import com.example.postcommentauth.comment.dto.CommentResponseDto;
import com.example.postcommentauth.comment.entity.Comment;
import com.example.postcommentauth.comment.repository.CommentRepository;
import com.example.postcommentauth.common.JwtUtil;
import com.example.postcommentauth.common.dto.StringResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.JwtDsl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    public CommentResponseDto commentCreate(CommentRequestDto requestDto, HttpServletRequest req) {
        Claims userInfo = userInfo(req);
        Comment newComment = new Comment(requestDto, userInfo.getSubject());
        return new CommentResponseDto(commentRepository.save(newComment));
    }

    @Transactional
    public CommentResponseDto commentUpdate(Long id, CommentRequestDto requestDto, HttpServletRequest req) {
        Claims userInfo = userInfo(req);
        Comment comment = findById(id);
        if (userInfo.getSubject().equals(comment.getUsername())) {
            comment.update(requestDto, userInfo.getSubject());
            return new CommentResponseDto(comment);
        } else {
            throw new IllegalArgumentException("유효하지 않은 회원정보입니다.");
        }
    }

    public StringResponseDto commentDelete(Long id, HttpServletRequest req) {
        Claims userInfo = userInfo(req);
        Comment comment = findById(id);
        if (userInfo.getSubject().equals(comment.getUsername())) {
            commentRepository.delete(comment);
            return new StringResponseDto("삭제 성공", "200");
        } else {
            throw new IllegalArgumentException("유효하지 않은 회원정보입니다.");
        }
    }

    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("유효하지 않은 댓글입니다")
        );
    }
    private Claims userInfo(HttpServletRequest req) {
        String givenToken = jwtUtil.getTokenFromRequest(req);
        givenToken = jwtUtil.substringToken(givenToken);
        if (!jwtUtil.validateToken(givenToken)) throw new IllegalArgumentException("Invalid User Credentials");
        return jwtUtil.getUserInfoFromToken(givenToken);
    }

}
