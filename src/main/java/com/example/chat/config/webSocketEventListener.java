package com.example.chat.config;

import com.example.chat.chat.ChatMessage;
import com.example.chat.chat.MessageType;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.logging.Logger;

@Component
public class webSocketEventListener {

    private static final Logger logger = Logger.getLogger(webSocketEventListener.class.getName());

    private final SimpMessageSendingOperations messageSendingOperations;

    // Constructor
    public webSocketEventListener(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent disconnectEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String userName = (String) stompHeaderAccessor.getSessionAttributes().get("username");

        if (userName != null) {
            logger.info("User disconnected: " + userName);

            ChatMessage chatMessage = new ChatMessage.Builder()
                    .type(MessageType.LEAVE)
                    .sender(userName)
                    .build();

            messageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}