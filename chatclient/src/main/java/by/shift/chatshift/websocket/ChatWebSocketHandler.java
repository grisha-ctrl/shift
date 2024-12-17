package by.shift.chatshift.websocket;


import by.shift.chatshift.model.Message;
import by.shift.chatshift.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    public static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper;
    private final MessageRepository messageRepository;
    private final Map<String, CustomSocket> socketMap = new HashMap<>();
    private Integer socketCounter = 0;

    public ChatWebSocketHandler(ObjectMapper objectMapper, MessageRepository messageRepository) {
        this.objectMapper = objectMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        try {
            sessions.add(session);
            socketMap.put(session.getId(), new CustomSocket(session));
            socketCounter++;
            System.out.println("WebSocket-клиент подключен: " + session.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message chatMessage = objectMapper.readValue(message.getPayload(), Message.class);
        chatMessage.setDate(LocalDateTime.now());
        messageRepository.saveMessage(chatMessage);
        System.out.println("Получено сообщение от UI: " + message.getPayload());
        CustomSocket customSocket = socketMap.get(session.getId());
        customSocket.getTcpOut().println(message.getPayload());
        session.sendMessage(new TextMessage(message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
        CustomSocket customSocket = socketMap.get(session.getId());
        customSocket.getTcpOut().println("close");
        socketMap.remove(session.getId());
    }

    private boolean containsUserName(String userName) {
        return socketMap.values().stream().noneMatch(customSocket -> customSocket.getUserName().equals(userName));
    }
}
