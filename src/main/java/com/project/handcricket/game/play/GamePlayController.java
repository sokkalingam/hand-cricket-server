package com.project.handcricket.game.play;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@MessageMapping("/game")
public class GamePlayController {

  @Autowired
  private GamePlayService gamePlayService;

  @RequestMapping("/restart/{gameId}")
  public void restartGame(@PathVariable String gameId) {
    gamePlayService.restartGame(gameId).getId();
  }

  @MessageMapping("/play/{gameId}/{playerId}")
  public void play(@DestinationVariable String gameId,
                   @DestinationVariable String playerId,
                   @RequestBody Integer input) {
    gamePlayService.gamePlay(gameId, playerId, input);
  }

}
