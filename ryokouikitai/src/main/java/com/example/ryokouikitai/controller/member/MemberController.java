package com.example.ryokouikitai.controller.member;

import com.example.ryokouikitai.dto.member.JoinForm;
import com.example.ryokouikitai.dto.member.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "members/login";
    }
    @PostMapping("/login")
    public String login(LoginForm loginForm){
        System.out.println("loginForm.getId() = " + loginForm.getId());
        System.out.println("loginForm.getPassword() = " + loginForm.getPassword());
        return "redirect:/members/login";
    }
    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinForm", new JoinForm());
        return "members/join";
    }
    @GetMapping("/mypage")
    public String mypage(){

        return "members/mypage";
    }
    @GetMapping("/mypage2")
    public String mypage2(){

        return "members/mypage2";
    }
    @GetMapping("/mypage3")
    public String mypage3(){

        return "members/mypage3";
    }
    @GetMapping("/mypage4")
    public String mypage4(){

        return "members/mypage4";
    }
    @GetMapping("/mypage5")
    public String mypage5(){

        return "members/mypage5";
    }
}
