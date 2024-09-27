package com.example.ryokouikitai.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class ChatController {
    @Autowired
    private RedisService redisService;

    // 지역별로 최근 채팅 기록을 가져오는 API
    @GetMapping("/chat/{area}/recent")
    @ResponseBody
    public List<String> getRecentMessages(@PathVariable("area") String area) {
        return redisService.getMessages(area, 5);  // 최근 5개의 메시지만 가져오기
    }
}
