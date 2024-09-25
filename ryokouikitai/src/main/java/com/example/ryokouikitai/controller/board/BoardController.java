package com.example.ryokouikitai.controller.board;

import com.example.ryokouikitai.dto.member.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")

public class BoardController {
    @GetMapping("/tip")
    public String getBoard1(){
        return "board/board1";
    }
    @GetMapping("/plan")
    public String getBoard2(){
        return "board/board2";
    }
    @GetMapping("/rank")
    public String getBoard3(){
        return "board/board3";
    }

    @GetMapping("tip/{board-id}")
    public String getBoard1Detail(@PathVariable("board-id") String boardId){
            return "board/board1detail";
    }

    @GetMapping("plan/{board-id}")
    public String getBoard2Detail(@PathVariable("board-id") String boardId){
        return "board/board2detail";
    }
}