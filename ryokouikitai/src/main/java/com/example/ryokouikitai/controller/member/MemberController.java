package com.example.ryokouikitai.controller.member;

import com.example.ryokouikitai.dto.member.JoinForm;
import com.example.ryokouikitai.dto.member.LoginForm;
import com.example.ryokouikitai.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm) {
        System.out.println("loginForm.getId() = " + loginForm.getId());
        System.out.println("loginForm.getPassword() = " + loginForm.getPassword());
        return "redirect:/members/login";
    }

    @GetMapping("/join")
    public String getJoin(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "members/join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm, BindingResult result) {

        if (!joinForm.getPassword().equals(joinForm.getPasswordCheck())) {
            System.out.println("비번같지않음");
            result.addError(new FieldError("joinForm", "passwordCheck", "비밀번호가 같지 않습니다."));
        }

        if(result.hasErrors()){
            System.out.println("에러가 있음");
            return "members/join";
        }
        memberService.join(joinForm);
        return "redirect:/members/login";
    }

    @GetMapping("/mypage")
    public String join() {

        return "members/mypage";
    }
}
