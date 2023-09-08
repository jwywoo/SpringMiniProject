package com.example.postcommentauth.member.service;

import com.example.postcommentauth.common.JwtUtil;
import com.example.postcommentauth.member.dto.SignupRequestDto;
import com.example.postcommentauth.member.dto.StringResponseDto;
import com.example.postcommentauth.member.entity.Member;
import com.example.postcommentauth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public StringResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // Duplication Check
        Optional<Member> memberCheck = memberRepository.findByUsername(username);
        if (memberCheck.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디");
        }

        Member newMember = new Member(username, password);
        memberRepository.save(newMember);
        return new StringResponseDto("회원가입 성공!","200");
    }
}
