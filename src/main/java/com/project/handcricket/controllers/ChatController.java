package com.project.handcricket.controllers;

import com.project.handcricket.models.Message;
import com.project.handcricket.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  @Autowired
  private ChatService chatService;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/chat/{gameId}/connect")
  public void connect(Message message, @DestinationVariable String gameId) {
    simpMessagingTemplate.convertAndSend("/chat/" + gameId, chatService.getConnectedMessage(message));
  }

  @MessageMapping("/chat/{gameId}")
  public void chat(Message message, @DestinationVariable String gameId) {
    simpMessagingTemplate.convertAndSend("/chat/" + gameId, message);
  }

  @MessageMapping("/chat/{gameId}/disconnect")
  public void disconnect(Message message, @DestinationVariable String gameId) {
    simpMessagingTemplate.convertAndSend("/chat/" + gameId, chatService.getDisconnectedMessage(message));
  }
}
