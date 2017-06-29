package com.project.handcricket.player.services;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.models.Game;
import com.project.handcricket.player.helpers.PlayerHelper;
import com.project.handcricket.player.helpers.PlayerNotificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerNotificationService {

  private GameDB gameDB;
  private SimpMessagingTemplate template;

  @Autowired
  public PlayerNotificationService(SimpMessagingTemplate template) {
    this.template = template;
    this.gameDB = GameDB.getInstance();
  }

  public void notifyGameRestart(String gameId) {
    Game game = GameDB.getInstance().getGame(gameId);
    String message = "Game has been Restarted";
    notifyPlayers(gameId, message);
    alertPlayers(gameId, message);
  }

  public void notifyPlayers(String gameId, String message) {
    Game game = GameDB.getInstance().getGame(gameId);
    notifyPlayer(gameId, game.getBatsman().getId(), message);
    notifyPlayer(gameId, game.getBowler().getId(), message);
  }

  public void alertPlayers(String gameId, String message) {
    Game game = GameDB.getInstance().getGame(gameId);
    alertPlayer(gameId, game.getBatsman().getId(), message);
    alertPlayer(gameId, game.getBowler().getId(), message);
  }

  public void notifyPlayer(String gameId, String playerId, String message) {
    Game game = GameDB.getInstance().getGame(gameId);
    if (game == null) return;
    template.convertAndSend("/player/notify/" + gameId + "/" + playerId,
        message);
  }

  public void alertPlayer(String gameId, String playerId, String message) {
    Game game = gameDB.getGame(gameId);
    if (game == null) return;
    template.convertAndSend("/player/alert/" + gameId + "/" + playerId, message);
  }

  public void notifyResult(String gameId, String playerId) {
    String baseDest = "/game/result/";
    template.convertAndSend(baseDest + gameId + "/" + playerId,
        PlayerNotificationHelper.getResultForPlayer(gameId, playerId));
    template.convertAndSend(baseDest + gameId + "/" + PlayerHelper.getOtherPlayer(gameId, playerId).getId(),
        PlayerNotificationHelper.getResultForPlayer(gameId, PlayerHelper.getOtherPlayer(gameId, playerId).getId()));
  }



}
