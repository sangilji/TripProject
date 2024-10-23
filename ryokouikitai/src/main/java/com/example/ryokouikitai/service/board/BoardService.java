package com.example.ryokouikitai.service.board;

import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.board.BoardComment;
import com.example.ryokouikitai.domain.board.BoardLike;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.board.BoardDetailDto;
import com.example.ryokouikitai.dto.board.BoardResponseDto;
import com.example.ryokouikitai.dto.board.CommentDto;
import com.example.ryokouikitai.dto.board.WriteForm;
import com.example.ryokouikitai.repository.area.ThemeRepository;
import com.example.ryokouikitai.repository.board.BoardCommentRepository;
import com.example.ryokouikitai.repository.board.BoardLikeRepository;
import com.example.ryokouikitai.repository.board.BoardRepository;
import com.example.ryokouikitai.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

//데이터베이스에 정보 넣는 작업
@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ThemeRepository themeRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BoardLikeRepository boardLikeRepository;

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

    public Page<BoardResponseDto> getAllByBoardName(Pageable pageable, String boardName) {
        return boardRepository.findAllByBoardMenu(boardName, pageable);
    }

    @Transactional
    public BoardDetailDto getById(MemberInfo memberInfo, String boardId) {
        Member member = memberRepository.findById(memberInfo.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. "));// IllegalArgument 임의로 에러명을 정의할 수 있음
        Board board = boardRepository.findByIdWithComment(Integer.valueOf(boardId));
        board.updateViewCount();
        boolean b = false;
        Optional<BoardLike> like = boardLikeRepository.findByMemberAndBoard(member, board);
        if (like.isPresent()) {
            b=like.get()
                    .getFlag();
        }
        return new BoardDetailDto(b, board);
    }

    @Transactional
    public CommentDto createComment(Integer id, String comment, String postId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. "));// IllegalArgument 임의로 에러명을 정의할 수 있음
        Board board = boardRepository.getReferenceById(Integer.valueOf(postId));
        BoardComment boardComment = BoardComment.builder()
                .board(board)
                .member(member)
                .createdAt(LocalDateTime.now())
                .content(comment)
                .build();
        return boardCommentRepository.save(boardComment).commentDto();

    }

    @Transactional
    public boolean likeBoard(Integer id, String postId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. "));// IllegalArgument 임의로 에러명을 정의할 수 있음
        Board board = boardRepository.getReferenceById(Integer.valueOf(postId));

        Optional<BoardLike> like = boardLikeRepository.findByMemberAndBoard(member, board);

        if (like.isPresent()) {
            BoardLike boardLike = like.get();
            int count = boardLike.changeLike();
            board.updateLikeCount(count);
            return boardLike.getFlag();
        } else {
            BoardLike boardLike = BoardLike.builder()
                    .board(board)
                    .member(member)
                    .flag(true)
                    .build();
            board.updateLikeCount(1);
            boardLikeRepository.save(boardLike);
            return true;
        }
    }

    @Transactional
    public String rewrite(WriteForm writeForm) {
        Board board = boardRepository.findById(writeForm.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. "));
        Category category = themeRepository.findByName(writeForm.getTheme()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테마"));
        board.update(writeForm, category);
        return board.getBoardMenu();
    }

    @Transactional
    public void deleteBoard(String postId) {
        Board board = boardRepository.findById(Integer.valueOf(postId))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. "));
        boardCommentRepository.deleteByBoard(board);
        boardLikeRepository.deleteByBoard(board);
        boardRepository.delete(board);
    }

    // 테마별로 정렬
    public Page<BoardResponseDto> getByTheme(String theme, Pageable pageable) {
        if(theme == null || theme.isEmpty()){
            return boardRepository.findAllByBoardMenu("plan", pageable);
        }
        return boardRepository.findAllByBoardMenuAndTheme("plan", pageable, theme);
    }

    public Page<BoardResponseDto> getByTitle(String title, String boardName, Pageable pageable) {
        return boardRepository.findAllByBoardMenuAndTitle(boardName, pageable, title);
    }

    public Page<Board> getByMemberId(Integer id, Pageable pageable) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. "));

        return boardRepository.findByMemberOrderByIdAsc(member, pageable);
    }

    public Page<Board> findLike(Integer id, Pageable pageable) {
        return boardRepository.findByLike(id, pageable);

    }
}
