package com.example.ryokouikitai.repository.board;

import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {

    void deleteByBoard(Board board);
}
