package com.example.postcommentauth.board.repository;

import com.example.postcommentauth.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
