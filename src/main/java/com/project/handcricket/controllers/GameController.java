package com.project.handcricket.controllers;

import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import com.project.handcricket.services.GameService;
import com.project.handcricket.services.PlayerService;
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
  private PlayerService playerService;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @RequestMapping(value = "/game/hostGame", method = RequestMethod.POST)
  public String getGameId(@RequestBody Player player) {
    return gameService.hostGame(player).getId();
  }

  @RequestMapping(value = "/game/joinGame/{gameId}", method = RequestMethod.POST)
  public Game joinGame(@PathVariable String gameId, @RequestBody Player player) {
    Game game = gameService.joinGame(gameId, player);
    publishGame(gameId);
    return game;
  }

  @MessageMapping("/game/{gameId}")
  public void publishGame(@DestinationVariable String gameId) {
    simpMessagingTemplate.convertAndSend("/game/" + gameId, gameService.getGame(gameId));
  }

  @MessageMapping("/game/play/{gameId}/{playerId}")
  public void play(@DestinationVariable String gameId, @DestinationVariable String playerId,
                        @RequestBody Integer input) {

    playerService.setInput(gameId, playerId, input);

    if (playerService.bothPlayersPlayed(gameId)) {
      gameService.play(gameId);
      publishGame(gameId);
      unpauseOtherPlayers(gameId, playerId);
      notifyResult(gameId, playerId);
    }
    else
      waitForPlay(gameId, playerId);

  }

  @RequestMapping("/game/activeGames")
  public Map<String, Game> getActiveGames() {
    return gameService.getActiveGames();
  }

  @RequestMapping("/game/addGame")
  public Game getGame() { return gameService.addGame(); }

  public void waitForPlay(String gameId, String playerId) {
    pauseCurrentPlayer(gameId, playerId);
    notifyCurrentPlayer(gameId, playerId);
    notifyOtherPlayer(gameId, playerId);
  }

  public void pauseCurrentPlayer(String gameId, String playerId) {
    simpMessagingTemplate.convertAndSend("/game/player/wait/" + gameId + "/" + playerId, true);
  }

  public void unpauseOtherPlayers(String gameId, String playerId) {
    simpMessagingTemplate.convertAndSend(
        "/game/player/wait/" + gameId + "/" + playerService.getPlayer(gameId, playerId).getId(),
        false);
    simpMessagingTemplate.convertAndSend(
        "/game/player/wait/" + gameId + "/" + playerService.getOtherPlayer(gameId, playerId).getId(),
        false);
  }

  public void notifyCurrentPlayer(String gameId, String playerId) {
    simpMessagingTemplate.convertAndSend("/game/player/notify/" + gameId + "/" + playerId,
        playerService.getMessageForCurrentPlayer(gameId, playerId));
  }

  public void notifyOtherPlayer(String gameId, String playerId) {
    simpMessagingTemplate.convertAndSend(
        "/game/player/notify/" + gameId + "/" + playerService.getOtherPlayer(gameId, playerId).getId(),
        playerService.getMessageForOtherPlayer(gameId, playerId));
  }

  public void notifyResult(String gameId, String playerId) {
    Player currentPlayer = playerService.getPlayer(gameId, playerId);
    Player otherPlayer = playerService.getOtherPlayer(gameId, playerId);
    simpMessagingTemplate.convertAndSend("/game/result/" + gameId + "/" + playerId,
        "You played " + currentPlayer.getLastDelivery() + ", " + otherPlayer.getName() + " played " + otherPlayer.getLastDelivery());
    simpMessagingTemplate.convertAndSend("/game/result/" + gameId + "/" + otherPlayer.getId(),
        "You played " + otherPlayer.getLastDelivery() + ", " + currentPlayer.getName() + " played " + currentPlayer.getLastDelivery());
  }

}
