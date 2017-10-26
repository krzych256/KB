package com.czat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.czat.model.UserChat;
import com.czat.service.AccessService;

@Controller
public class CzatController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AccessService accessService;

  @MessageMapping("/chat.addUser")
  public void addUserToQueue(@Payload UserChat userChat, SimpMessageHeaderAccessor headerAccessor) {

    accessService.addUserToChat(userChat, headerAccessor.getSessionId());
    logger.info("Add new user to chat: " + userChat.getUsername());
    
  }
  
  @MessageMapping("/chat.sendMessage")
  @SendTo("/channel/public")
  public UserChat sendMessage(@Payload UserChat userChat) {
    
    //accessService.sendMessage(userChat);
    return userChat;
    
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    accessService.deleteConnection(headerAccessor.getSessionId());
    logger.info("Disconnect user: " + headerAccessor.getSessionAttributes().get("userName"));

  }

}