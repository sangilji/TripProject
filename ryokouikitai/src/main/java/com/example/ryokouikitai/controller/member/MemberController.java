package com.example.ryokouikitai.controller.member;

import com.example.ryokouikitai.domian.member.JoinForm;
import com.example.ryokouikitai.domian.member.LoginForm;
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
}
