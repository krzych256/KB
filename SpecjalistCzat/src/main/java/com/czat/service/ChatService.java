package com.czat.service;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

  private Deque<String> chatQueue;

  @PostConstruct
  private void init() {
    this.chatQueue = new ArrayDeque<>();
  }

  public synchronized void addToQueue(String name) {
    this.chatQueue.add(name);
  }
  
  public synchronized boolean deleteUserIfFirst(String sessionId) {
    if(this.chatQueue.getFirst().equals(sessionId)) {
      this.chatQueue.pop();
      return true;
    }
    return false;
  }
    
  public synchronized Deque<String> getChatQueue() {
    return chatQueue;
  }

  public synchronized void setChatQueue(Deque<String> chatQueue) {
    this.chatQueue = chatQueue;
  }
  
}
