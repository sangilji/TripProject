package com.example.ryokouikitai.repository.board;

import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.board.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("select new com.example.ryokouikitai.dto.board.BoardResponseDto(b, count(c)) " +
            "from Board b left join BoardComment c on c.board = b where b.boardMenu = :boardName " +
            "group by b")
    Page<BoardResponseDto> findAllByBoardMenu(@Param("boardName") String boardName, Pageable pageable);
//    DB 역할 수행

    @Query("select b from Board b left join fetch b.boardComments where b.id = :id ")
    Board findByIdWithComment(@Param("id") Integer id);

    @Query("select new com.example.ryokouikitai.dto.board.BoardResponseDto(b, count(c)) " +
            "from Board b left join BoardComment c on c.board = b where b.boardMenu = :boardName and b.theme.name like %:theme% and b.title like  %:title% " +
            "group by b")
    Page<BoardResponseDto> findAllByBoardMenuAndTheme(@Param("boardName") String boardName, @Param("title") String title, @Param("theme") String theme, Pageable pageable);
    
    // 검색 기능 구현
    @Query("select new com.example.ryokouikitai.dto.board.BoardResponseDto(b, count(c)) " +
            "from Board b left join BoardComment c on c.board = b where b.boardMenu = :boardName and b.title like %:title% " +
            "group by b")
    Page<BoardResponseDto> findAllByBoardMenuAndTitle(@Param("boardName")String boardName, Pageable pageable, @Param("title") String title);

    Page<Board> findByMemberOrderByIdAsc(Member member, Pageable pageable);

//    :을 하면 param에 있는 값을 가져오겠다라는 뜻
    @Query("select b from BoardLike l inner join Board b on l.board = b where l.flag = true and l.member.id=:id")
    Page<Board> findByLike(@Param("id")Integer id, Pageable pageable);

}
