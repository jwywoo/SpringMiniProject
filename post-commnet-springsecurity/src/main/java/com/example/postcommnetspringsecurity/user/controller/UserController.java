package com.example.postcommnetspringsecurity.user.controller;

import com.example.postcommnetspringsecurity.common.dto.StringResponseDto;
import com.example.postcommnetspringsecurity.user.dto.SignupRequestDto;
import com.example.postcommnetspringsecurity.user.service.UserService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Signup
    @PostMapping("/user/signup")
    public StringResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new StringResponseDto("유효하지 않은 회원정보입니다.", "400");
        }

        return userService.signup(requestDto);
    }
}
