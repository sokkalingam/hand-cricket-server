package com.project.handcricket.chat;

import com.project.handcricket.models.Message;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

  public Message getConnectedMessage(Message message) {
    return _getStatusMessage(message, "connected to chat");
  }

  public Message getDisconnectedMessage(Message message) {
    return _getStatusMessage(message, "disconnected from chat");
  }

  private Message _getStatusMessage(Message message, String customMessage) {
    message.setMessage(message.getSenderName() + " " + customMessage);
    message.setSenderId("CHATBOT");
    message.setSenderName("CHATBOT");
    return message;
  }

}
