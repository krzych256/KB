package com.czat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.czat.model.MessageChat;
import com.czat.service.ChatService;

@Controller
public class CzatController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
  @Autowired
  private ChatService chatService;
     
  @MessageMapping("/chat.addUser")
  @SendTo("/channel/public")
  public MessageChat addUserToQueue(@Payload MessageChat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    
    chatService.addToQueue(headerAccessor.getSessionId());
    
    if(chatService.getChatQueue().getFirst().equals(headerAccessor.getSessionId())) {
      MessageChat messageChat = new MessageChat();
      messageChat.setSessionId(headerAccessor.getSessionId());
      messageChat.setType(MessageChat.MessageType.JOIN);
      messageChat.setSender(chatMessage.getSender());
      
      logger.info("Id Sesji: " + headerAccessor.getSessionId() + " username: " + chatMessage.getSender() + " type: " + messageChat.getType());
      
      return messageChat;
    }
        
    chatMessage.setType(MessageChat.MessageType.BLOCK);
    logger.info("Id Sesji: " + headerAccessor.getSessionId() + " username: " + chatMessage.getSender() + " type: " + chatMessage.getType());
    
    return chatMessage;
  }
  
  
  
  
  
  
  
  
  
}
