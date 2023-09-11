package com.example.postcommnetspringsecurity.user.service;

import com.example.postcommnetspringsecurity.common.dto.StringResponseDto;
import com.example.postcommnetspringsecurity.user.dto.SignupRequestDto;
import com.example.postcommnetspringsecurity.user.entity.User;
import com.example.postcommnetspringsecurity.user.entity.UserRoleEnum;
import com.example.postcommnetspringsecurity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    public StringResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // Duplication check
        Optional<User> usernameCheck = userRepository.findByUsername(username);
        if (usernameCheck.isPresent()) {
            throw new IllegalArgumentException("Please use different name");
        }

        // User Role check
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("Invalid Credentials");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username,password,role);
        userRepository.save(user);
        return new StringResponseDto("Success", "200");
    }
}
