package com.example.ryokouikitai.service.board;

import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.board.WriteForm;
import com.example.ryokouikitai.repository.area.ThemeRepository;
import com.example.ryokouikitai.repository.board.BoardRepository;
import com.example.ryokouikitai.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//데이터베이스에 정보 넣는 작업
@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ThemeRepository themeRepository;

    @Transactional
    public Board write(Integer id, WriteForm writeForm) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원 정보 오류"));
        Category category = themeRepository.findByName(writeForm.getTheme()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테마"));
        Board board = Board
                .builder()
                .member(member)
                .theme(category)
                .content(writeForm.getContent())
                .title(writeForm.getTitle())
                .likeCount(0)
                .viewCount(0)
                .boardMenu(writeForm.getBoardName())
                .flag((false))
                .createdAt(LocalDateTime.now())
                .build();
        boardRepository.save(board);
        return board;
    }

    public Page<Board> getAllByBoardName(Pageable pageable, String boardName) {
        return boardRepository.findAllByBoardMenu(boardName,pageable);
    }

    public Board getById(String boardId) {
        return boardRepository.findById(Integer.valueOf(boardId)).orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
}
