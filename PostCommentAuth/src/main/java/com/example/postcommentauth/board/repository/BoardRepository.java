package com.example.postcommentauth.board.repository;

import com.example.postcommentauth.board.dto.BoardResponseDto;
import com.example.postcommentauth.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
}
