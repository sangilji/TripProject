package com.example.ryokouikitai.controller.accompany;


import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.accompany.WriteForm;
import com.example.ryokouikitai.global.response.BaseResponse;
import com.example.ryokouikitai.service.accompany.AccompanyService;
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
}
