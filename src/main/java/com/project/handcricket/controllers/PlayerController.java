package com.project.handcricket.controllers;

import com.project.handcricket.services.GameService;
import com.project.handcricket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

  private PlayerService playerService;
  private GameService gameService;
  private SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  public PlayerController(PlayerService playerService, GameService gameService,
                          SimpMessagingTemplate simpMessagingTemplate) {
    this.playerService = playerService;
    this.gameService = gameService;
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

}
