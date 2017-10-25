package com.czat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.czat.model.MessageChat;
import com.czat.model.MessageChat.MessageType;
import com.czat.service.ChatService;

@Component
public class WebSocketEventListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ChatService chatService;

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

    System.out.println(headerAccessor.getSessionId());
    
    if (chatService.deleteUserIfFirst(headerAccessor.getSessionId())) {
      
      MessageChat messageChat = new MessageChat();
      messageChat.setSender("2");
      messageChat.setType(MessageChat.MessageType.JOIN);
      
      messageChat.setSessionId(chatService.getChatQueue().getFirst());
      
      messagingTemplate.convertAndSend("/channel/public", messageChat);
     // messagingTemplate.convertAndSendToUser(arg0, arg1, arg2);
      
      
    }

    
  }
}
