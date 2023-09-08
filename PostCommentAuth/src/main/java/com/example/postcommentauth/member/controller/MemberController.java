package com.example.postcommentauth.member.controller;

import com.example.postcommentauth.member.dto.SignupRequestDto;
import com.example.postcommentauth.member.dto.StringResponseDto;
import com.example.postcommentauth.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<StringResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok(memberService.signup(requestDto));
    }
}
