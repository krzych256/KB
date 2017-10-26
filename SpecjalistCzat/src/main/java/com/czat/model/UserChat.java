package com.czat.model;

public class UserChat {

  private String username;
  private String sessionId;
  private String content;
  private MessageType messageType;
  
  public UserChat() { }
  
  public UserChat(String username, String sessionId, MessageType messageType ) {
    this.username = username;
    this.sessionId = sessionId;
    this.messageType = messageType;
  }

  public enum MessageType {
    CHAT, JOIN, LEAVE, BLOCK, ACCESS
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }  
    
}
