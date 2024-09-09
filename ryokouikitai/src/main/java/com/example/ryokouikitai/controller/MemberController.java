package com.example.ryokouikitai.controller;

import com.example.ryokouikitai.domian.member.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/api")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/members/login")
    public String createForm(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "members/login";
    }

    @PostMapping("/members/login")
    public String createForm(LoginForm loginForm){
        System.out.println("loginForm.getId() = " + loginForm.getId());
        System.out.println("loginForm.getPassword() = " + loginForm.getPassword());
        return "members/login";
    }
}
