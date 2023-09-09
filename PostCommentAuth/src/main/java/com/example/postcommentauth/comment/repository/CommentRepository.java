package com.example.postcommentauth.comment.repository;

import com.example.postcommentauth.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
