package com.example.postcommentauth.member.service;

import com.example.postcommentauth.common.JwtUtil;
import com.example.postcommentauth.member.dto.LoginRequestDto;
import com.example.postcommentauth.member.dto.SignupRequestDto;
import com.example.postcommentauth.common.dto.StringResponseDto;
import com.example.postcommentauth.member.entity.Member;
import com.example.postcommentauth.member.entity.MemberRoleEnum;
import com.example.postcommentauth.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
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

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public StringResponseDto signup(SignupRequestDto requestDto) {
        System.out.println("service");
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // Duplication Check
        Optional<Member> memberCheck = memberRepository.findByUsername(username);
        if (memberCheck.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디");
        }

        // Member role assignment
        MemberRoleEnum role = MemberRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = MemberRoleEnum.ADMIN;
        }

        Member newMember = new Member(username, password, role);
        memberRepository.save(newMember);
        return new StringResponseDto("회원가입 성공!","200");
    }

    public StringResponseDto login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        // Username & Password Check
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 아이디/비밀번호")
        );
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("유효하지 않은 아이디/비밀번호");
        }
        // Token creation and insertion
        String token = jwtUtil.createToken(member.getUsername(), member.getRole());
        jwtUtil.addJwtToCookie(token, res);
        return new StringResponseDto("로그인 성공!","200");
    }
}
