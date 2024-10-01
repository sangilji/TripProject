package com.example.ryokouikitai.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    @Autowired
    private RedisService redisService;

    // 지역별로 세션을 관리할 Map (지역 이름 -> 세션 리스트)
    private static Map<String, List<WebSocketSession>> areaSessions = new ConcurrentHashMap<>();

    // 연결(클라이언트 접속)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String area = getAreaNameFromSession(session);
        areaSessions.putIfAbsent(area, new ArrayList<>());
        areaSessions.get(area).add(session);

        // 입장한 클라이언트에게 이전 채팅 기록 제공
        List<String> previousMessages = redisService.getMessages(area, 50);  // 최근 50개 메시지 불러오기
        for (String msg : previousMessages) {
            session.sendMessage(new TextMessage(msg));
        }

        log.info("[{}] 클라이언트 접속 in area: {}", session, area);
    }

    // 연결 종료(클라이언트 접속 해제)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String area = getAreaNameFromSession(session);
        log.info("[{}] 클라이언트 접속 해제 in area: {}", session, area);
        areaSessions.get(area).remove(session);  // 해당 지역에서 세션 제거
    }

    // 메시지 수신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String area = getAreaNameFromSession(session);
        String payload = message.getPayload();
        log.info("Received payload: {} in area: {}", payload, area);

        // 메시지를 Redis에 저장
        redisService.saveMessage(area, payload);

        // 해당 지역의 모든 세션에 메시지 전송
        for (WebSocketSession webSocketSession : areaSessions.get(area)) {
            webSocketSession.sendMessage(message);
        }
    }

    // 오류 처리
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Error in session: {}", session, exception);
    }

    // WebSocketSession에서 URI를 통해 지역 이름 추출
    private String getAreaNameFromSession(WebSocketSession session) {
        String uri = session.getUri().toString();
        return uri.split("/")[uri.split("/").length - 1];  // 마지막 부분이 지역 이름으로 가정
    }
}
