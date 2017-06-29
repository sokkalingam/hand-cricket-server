package com.project.handcricket.game.disconnect.controllers;

import com.project.handcricket.game.disconnect.services.GameDisconnectService;
import com.project.handcricket.player.helpers.PlayerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("/game")
@RequestMapping("/game")
public class GameDisconnectController {

  @Autowired
  private GameDisconnectService gameDisconnectService;

  @MessageMapping("/quit/{gameId}")
  public void quitGame(@DestinationVariable String gameId, @RequestBody String playerId) {
    gameDisconnectService.disconnect(gameId, playerId);
  }

}
