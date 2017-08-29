package com.project.handcricket.player.services;

import com.project.handcricket.data.GameData;
import com.project.handcricket.model.Game;
import com.project.handcricket.player.helpers.PlayerHelper;
import com.project.handcricket.player.helpers.PlayerNotificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerNotificationService {

  private GameData gameData;
  private SimpMessagingTemplate template;

  @Autowired
  public PlayerNotificationService(SimpMessagingTemplate template) {
    this.template = template;
    this.gameData = GameData.getInstance();
  }

  public void notifyGameRestart(String gameId) {
    String message = "Game has been Restarted";
    notifyPlayers(gameId, message);
    alertPlayers(gameId, "");
  }

  public void notifyPlayers(String gameId, String message) {
    Game game = GameData.getInstance().getGame(gameId);
    notifyPlayer(gameId, game.getBatsman().getId(), message);
    notifyPlayer(gameId, game.getBowler().getId(), message);
  }

  public void alertOut(String gameId) {
    Game game = GameData.getInstance().getGame(gameId);
    alertPlayer(gameId, game.getBatsman().getId(), PlayerNotificationHelper.getOutMsg(gameId, game.getBatsman().getId()));
    alertPlayer(gameId, game.getBowler().getId(), PlayerNotificationHelper.getOutMsg(gameId, game.getBowler().getId()));
  }

  public void alertPlayers(String gameId, String message) {
    Game game = GameData.getInstance().getGame(gameId);
    alertPlayer(gameId, game.getBatsman().getId(), message);
    alertPlayer(gameId, game.getBowler().getId(), message);
  }

  public void notifyPlayer(String gameId, String playerId, String message) {
    template.convertAndSend("/game/" + gameId + "/player/" + playerId + "/notify", message);
  }

  public void alertPlayer(String gameId, String playerId, String message) {
    template.convertAndSend("/game/" + gameId + "/player/" + playerId + "/alert", message);
  }

  public void notifyResult(String gameId, String playerId) {
    String baseDest = "/game/result/";
    template.convertAndSend(baseDest + gameId + "/" + playerId,
        PlayerNotificationHelper.getResultForPlayer(gameId, playerId));
    template.convertAndSend(baseDest + gameId + "/" + PlayerHelper.getOtherPlayer(gameId, playerId).getId(),
        PlayerNotificationHelper.getResultForPlayer(gameId, PlayerHelper.getOtherPlayer(gameId, playerId).getId()));
  }

}
