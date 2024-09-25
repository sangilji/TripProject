package com.example.ryokouikitai.controller.accompany;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accompany")
public class AccompanyController {


    @GetMapping()
    public String getAccompanyPage() {
        return "accompany/accompanyMain";
    }


    @GetMapping("/write")
    public String getWriteAccompanyPage() {
        return "accompany/write";
    }
}
