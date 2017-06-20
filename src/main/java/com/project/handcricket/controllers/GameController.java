package com.project.handcricket.controllers;

import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import com.project.handcricket.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GameController {

  @Autowired
  private GameService gameService;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @RequestMapping(value = "/hostGame", method = RequestMethod.POST)
  public String getGameId(@RequestBody Player player) {
    return gameService.hostGame(player).getId();
  }

  @RequestMapping(value = "/joinGame/{gameId}", method = RequestMethod.POST)
  public Game joinGame(@PathVariable String gameId, @RequestBody Player player) {
    Game game = gameService.joinGame(gameId, player);
    publishGame(gameId);
    return game;
  }

  @MessageMapping("/game/{gameId}")
  public void publishGame(@DestinationVariable String gameId) {
    simpMessagingTemplate.convertAndSend("/game/" + gameId, gameService.getGame(gameId));
  }

  @MessageMapping("/game/{gameId}/{playerId}/play")
  public void playInput(@DestinationVariable String gameId, @DestinationVariable String playerId,
                        @RequestBody Integer input) {
    simpMessagingTemplate.convertAndSend("/game/" + gameId + "/" + playerId, "");
  }

  @RequestMapping("/activeGames")
  public Map<String, Game> getActiveGames() {
    return gameService.getActiveGames();
  }

  @RequestMapping("/addGame")
  public Game getGame() { return gameService.addGame(); }

}
