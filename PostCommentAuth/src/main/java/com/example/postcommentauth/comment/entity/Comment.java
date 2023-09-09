package com.example.postcommentauth.comment.entity;

import com.example.postcommentauth.comment.dto.CommentRequestDto;
import com.example.postcommentauth.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    public Comment(CommentRequestDto requestDto, String username) {
        this.username = username;
        this.content = requestDto.getContent();
    }

    public void update(CommentRequestDto requestDto, String username) {
        this.content = requestDto.getContent();
        this.username = username;
    }
}
