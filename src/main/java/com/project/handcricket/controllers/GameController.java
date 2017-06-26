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


  @RequestMapping("/game/activeGames")
  public Map<String, Game> getActiveGames() {
    return gameService.getActiveGames();
  }

  @RequestMapping("/game/addGame")
  public Game getGame() { return gameService.addGame(); }

  @RequestMapping("/game/{gameId}/restart")
  public String restartGame(@PathVariable String gameId) {
    publishGame(gameService.restartGame(gameId).getId());
    notifyGameRestart(gameId);
    return "Game Restarted!";
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
      if (playerService.isSameInput(gameService.getGame(gameId)))
        notifyHighlights(gameId, playerId);
      gameService.play(gameId);
      publishGame(gameId);
      notifyResult(gameId, playerId);
      unpauseOtherPlayers(gameId, playerId);
    }
    else
      waitForPlay(gameId, playerId);

  }

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

  public void notifyGameRestart(String gameId) {
    Game game = gameService.getGame(gameId);
    String message = "Game has been Restarted";
    simpMessagingTemplate.convertAndSend("/game/player/notify/" + gameId + "/" + game.getBatsman().getId(),
        message);
    simpMessagingTemplate.convertAndSend("/game/player/notify/" + gameId + "/" + game.getBowler().getId(),
        message);
    simpMessagingTemplate.convertAndSend("/game/highlight/" + gameId + "/" + game.getBatsman().getId(), "");
    simpMessagingTemplate.convertAndSend("/game/highlight/" + gameId + "/" + game.getBowler().getId(), "");
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
    simpMessagingTemplate.convertAndSend("/game/result/" + gameId + "/" + playerId,
        playerService.getResultForPlayer(gameId, playerId));
    simpMessagingTemplate.convertAndSend("/game/result/" + gameId + "/" + playerService.getOtherPlayer(gameId, playerId).getId(),
        playerService.getResultForPlayer(gameId, playerService.getOtherPlayer(gameId, playerId).getId()));
  }

  public void notifyHighlights(String gameId, String playerId) {
    simpMessagingTemplate.convertAndSend("/game/highlight/" + gameId + "/" + playerId,
        playerService.getHighlightMessage(gameId, playerId));
    simpMessagingTemplate.convertAndSend("/game/highlight/" + gameId + "/" + playerService.getOtherPlayer(gameId, playerId).getId(),
        playerService.getHighlightMessage(gameId, playerService.getOtherPlayer(gameId, playerId).getId()));
  }

}
