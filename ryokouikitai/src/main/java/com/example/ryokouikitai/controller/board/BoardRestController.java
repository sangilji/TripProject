package com.example.ryokouikitai.controller.board;

import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.board.CommentDto;
import com.example.ryokouikitai.dto.board.WriteForm;
import com.example.ryokouikitai.global.response.BaseResponse;
import com.example.ryokouikitai.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
//    데이터를 주고 받는 요청 수행
public class BoardRestController {
//    서비스 연결
    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<?> write(HttpSession session, WriteForm writeForm) {
//        로그인은 항상 Session에서 관리
        log.info("writeForm{}", writeForm);
//        로그인할 때 넣은 본인 정보
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Board board = boardService.write(memberInfo.getId(), writeForm);

        return BaseResponse.okWithData(HttpStatus.OK,"게시글 저장 완료", board);
    }

    @PutMapping("/write")
    public ResponseEntity<?> rewrite(WriteForm writeForm) {
//        로그인은 항상 Session에서 관리
        String boardMenu = boardService.rewrite(writeForm);

        return BaseResponse.okWithData(HttpStatus.OK,"게시글 수정 완료",boardMenu);
    }

    @PostMapping("/{postId}/createComment")
    public ResponseEntity<?> createComment(HttpSession session, String comment, @PathVariable String postId) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        CommentDto commentDto = boardService.createComment(memberInfo.getId(), comment, postId);
        return BaseResponse.okWithData(HttpStatus.OK,"댓글 저장 완료", commentDto);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<?> like(HttpSession session, @PathVariable String postId) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        boolean b = boardService.likeBoard(memberInfo.getId(), postId);
        return BaseResponse.okWithData(HttpStatus.OK,"좋아요", b);
    }

    @DeleteMapping("/tip/{postId}")
    public ResponseEntity<?> delete(@PathVariable String postId) {
        boardService.deleteBoard(postId);
        return BaseResponse.ok(HttpStatus.OK,"좋아요");
    }
}
