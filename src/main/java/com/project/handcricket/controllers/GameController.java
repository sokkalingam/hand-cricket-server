package com.project.handcricket.controllers;

import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import com.project.handcricket.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

  @Autowired
  private GameService gameService;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @RequestMapping("/getGameId")
  public String getGameId() {
    return gameService.getGameId();
  }

  @MessageMapping("/game/{gameId}/{playerId}")
  public void getPlayerUpdate(Player player, @DestinationVariable String gameId, @DestinationVariable String playerId) {
    simpMessagingTemplate.convertAndSend("/live-updates/"+gameId+"/"+playerId, "Hello User!, PlayerId: " + playerId + ", GameId: " + gameId);
    simpMessagingTemplate.convertAndSend("/live-updates/"+gameId, "Hello Gamer!, PlayerId: " + playerId + ", GameId: " + gameId);
  }

}
