package com.example.ryokouikitai.controller.board;

import com.example.ryokouikitai.dto.member.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")

public class BoardController {
    @GetMapping("/board1")
    public String getBoard1(){
        return "board/board1";
    }
    @GetMapping("/board2")
    public String getBoard2(){
        return "board/board2";
    }
    @GetMapping("/board3")
    public String getBoard3(){
        return "board/board3";
    }
}
