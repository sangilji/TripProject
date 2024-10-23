package com.example.ryokouikitai.repository.member;

import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.board.BoardResponseDto;
import com.example.ryokouikitai.dto.member.MemberResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByUserId(String userId);

    boolean existsByNickname(String email);

    Optional<Member> findByUserIdAndPassword(String userId, String password);

//    Page<MemberResponseDto> findAllByBoardBar(String boardBar);

//    @Query("select new com.example.ryokouikitai.dto.board.MemberResponseDto(b, count(c)) " +
//            "from Member m left join BoardComment c on c.member = m where m.boardBar = :boardBar and b.theme.name= :theme " +
//            "group by b")
//    Page<BoardResponseDto> findAllByBoardMenuAndTheme(@Param("boardName") String boardName, Pageable pageable, @Param("theme") String theme);
}
