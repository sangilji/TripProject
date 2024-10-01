package com.example.ryokouikitai.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 채팅 메시지 저장
    public void saveMessage(String area, String message) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        listOps.rightPush("chat:" + area, message);  // 해당 지역의 채팅 기록에 메시지 추가
    }

    // 채팅 메시지 조회 (최근 50개 메시지 불러오기)
    public List<String> getMessages(String area, int count) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.range("chat:" + area, -count, -1);  // 최신 count개의 메시지 반환
    }
}

