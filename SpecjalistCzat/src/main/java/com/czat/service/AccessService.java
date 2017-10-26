package com.czat.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;

import com.czat.model.UserChat;

@Service
public class AccessService {

  private Deque<UserChat> chatQueue;
  private List<String> toDeleteUser;

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @PostConstruct
  private void init() {
    chatQueue = new ArrayDeque<>();
    toDeleteUser = new ArrayList<>();
  }

  public void addUserToChat(UserChat userChat, String userSessionId) {

    if (userChat.getUsername().equals("ADMIN")) {
      UserChat userChatFull = new UserChat(userChat.getUsername(), userSessionId,
          userChat.getMessageType());
      userChatFull.setMessageType(UserChat.MessageType.ACCESS);
      messagingTemplate.convertAndSendToUser(userChatFull.getSessionId(), "/queue/response", userChatFull,
          createHeaders(userChatFull.getSessionId()));
      
    } else {
      
      UserChat userChatFull = new UserChat(userChat.getUsername(), userSessionId,
          userChat.getMessageType());
      userChatFull.setSessionId(userSessionId);

      chatQueue.addLast(userChatFull);
      connectToChat(userChatFull);
    }
  }

  public void sendMessage(UserChat userChat) {
    messagingTemplate.convertAndSendToUser(userChat.getSessionId(), "/queue/response", userChat,
        createHeaders(userChat.getSessionId()));
  }

  public void connectToChat(UserChat userChat) {

    checkIfUserIsInDeleteList();

    if (!chatQueue.isEmpty()) {

      if (chatQueue.getFirst().getSessionId().equals(userChat.getSessionId())) {
        userChat.setMessageType(UserChat.MessageType.ACCESS);
        messagingTemplate.convertAndSendToUser(userChat.getSessionId(), "/queue/response", userChat,
            createHeaders(userChat.getSessionId()));
      } else {
        userChat.setMessageType(UserChat.MessageType.BLOCK);
        messagingTemplate.convertAndSendToUser(userChat.getSessionId(), "/queue/response", userChat,
            createHeaders(userChat.getSessionId()));
      }
    }
  }

  public void deleteConnection(String sessionId) {
    if (chatQueue.getFirst().getSessionId().equals(sessionId)) {
      chatQueue.pop();
      if (!chatQueue.isEmpty()) {
        checkIfUserIsInDeleteList();
        connectToChat(chatQueue.getFirst());
      }
    } else
      toDeleteUser.add(sessionId);
  }

  public void checkIfUserIsInDeleteList() {
    if (!toDeleteUser.isEmpty()) {
      for (int i = 0; i < toDeleteUser.size(); i++) {
        if (chatQueue.getFirst().getSessionId().equals(toDeleteUser.get(i))) {
          chatQueue.pop();
        }
      }
    }
  }

  private MessageHeaders createHeaders(String sessionId) {
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
        .create(SimpMessageType.MESSAGE);
    headerAccessor.setSessionId(sessionId);
    headerAccessor.setLeaveMutable(true);
    return headerAccessor.getMessageHeaders();
  }
}
