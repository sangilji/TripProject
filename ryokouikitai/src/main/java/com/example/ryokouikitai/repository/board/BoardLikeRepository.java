package com.example.ryokouikitai.repository.board;

import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.board.BoardComment;
import com.example.ryokouikitai.domain.board.BoardLike;
import com.example.ryokouikitai.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Integer> {

    Optional<BoardLike> findByMemberAndBoard(Member member, Board board);
    void deleteByBoard(Board board);

}
