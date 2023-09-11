package com.example.postcommnetspringsecurity.user.repository;

import com.example.postcommnetspringsecurity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
