package com.example.ryokouikitai.repository.board;

import com.example.ryokouikitai.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("select new com.example.ryokouikitai.dto.board.BoardResponseDto(b, count(c)) " +
            "from Board b left join BoardComment c on c.board = b where b.boardMenu = :boardName " +
            "group by b")
    Page<Board> findAllByBoardMenu(@Param("boardName") String boardName, Pageable pageable);
//    DB 역할 수행

@Query("select b from Board b left join fetch b.boardComments where b.id = :id ")
    Board findByIdWithComment(@Param("id") Integer id);

}
