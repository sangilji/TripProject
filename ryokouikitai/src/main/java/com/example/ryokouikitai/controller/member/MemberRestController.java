package com.example.ryokouikitai.controller.member;


import com.example.ryokouikitai.dto.member.JoinForm;
import com.example.ryokouikitai.global.response.BaseResponse;
import com.example.ryokouikitai.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberRestController {

    private final MemberService memberService;


    @PostMapping("/join/duplicated-id")
    public ResponseEntity<?> joinDuplicatedId(String userId) {

        log.info("아이디 중복확인");
        if (memberService.existId(userId)) {
            return BaseResponse.fail("중복된 아이디", 400);
        }
        return BaseResponse.ok(HttpStatus.OK,"중복된 아이디가 없습니다.");
    }

    @PostMapping("/join/duplicated-nickname")
    public ResponseEntity<?> joinDuplicatedNickname(String nickname) {

        log.info("이메일 중복확인");
        if (memberService.existNickname(nickname)) {
            return BaseResponse.fail("중복된 이메일", 400);
        }
        return BaseResponse.ok(HttpStatus.OK,"사용가능한 이메일입니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return BaseResponse.ok(HttpStatus.OK,"로그아웃");
    }
}
