package com.example.ryokouikitai.controller.accompany;


import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.accompany.AccompanyComment;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.accompany.CommentDto;
import com.example.ryokouikitai.dto.accompany.WriteForm;
import com.example.ryokouikitai.global.response.BaseResponse;
import com.example.ryokouikitai.service.accompany.AccompanyService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accompany")
@Slf4j
public class AccompanyRestController {

    private final AccompanyService accompanyService;

    @PostMapping("/write")
    public ResponseEntity<?> write(HttpSession session, WriteForm writeForm) {
        log.info("writeForm {}", writeForm);
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Accompany accompany = accompanyService.write(memberInfo.getId(), writeForm);

        return BaseResponse.okWithData(HttpStatus.OK, "게시글 저장완료", accompany);
    }

    @PutMapping("/write")
    public ResponseEntity<?> rewrite(WriteForm writeForm) {

        accompanyService.rewrite(writeForm);

        return BaseResponse.ok(HttpStatus.OK, "게시글 수정");
    }

    @PostMapping("/{postId}/createComment")
    public ResponseEntity<?> createComment(HttpSession session, String comment, @PathVariable String postId) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        CommentDto commentDto = accompanyService.createComment(memberInfo.getId(), comment, postId);
        return BaseResponse.okWithData(HttpStatus.OK, "댓글 저장완료", commentDto);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<?> like(HttpSession session, @PathVariable String postId) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        boolean b = accompanyService.likeAccompany(memberInfo.getId(), postId);
        return BaseResponse.okWithData(HttpStatus.OK, "좋아요", b);
    }
}
