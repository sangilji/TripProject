package com.example.ryokouikitai.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
@Slf4j
public class ChatController {
    @GetMapping("/chat")
    public String chatGet(Model model) {
        log.info("ChatController Test");

        model.addAttribute("name", UUID.randomUUID().toString());
        return "chat";
    }
}
