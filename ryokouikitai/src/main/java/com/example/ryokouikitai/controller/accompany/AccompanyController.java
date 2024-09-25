package com.example.ryokouikitai.controller.accompany;

import com.example.ryokouikitai.dto.member.JoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accompany")
public class AccompanyController {


    @GetMapping()
    public String getAccompanyPage() {
        return "accompany/accompany";
    }
}
