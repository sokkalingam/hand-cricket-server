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
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/chat/{gameId}")
  public void chat(Message message, @DestinationVariable String gameId) {
    simpMessagingTemplate.convertAndSend("/chat/" + gameId, message);
  }
}
