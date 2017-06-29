package com.project.handcricket.game.play;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.models.Game;
import com.project.handcricket.player.helpers.PlayerHelper;
import com.project.handcricket.player.helpers.PlayerNotificationHelper;
import com.project.handcricket.player.services.PlayerNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GamePlayService {

  private PlayerNotificationService playerNotificationService;
  private SimpMessagingTemplate template;
  private GameDB gameDB;

  @Autowired
  public GamePlayService(PlayerNotificationService playerNotificationService,
                         SimpMessagingTemplate template) {
    this.playerNotificationService = playerNotificationService;
    this.template = template;
    this.gameDB = GameDB.getInstance();
  }

  public Game play(String gameId) {
    Game game = gameDB.getGame(gameId);
    PlayerHelper.addBalls(game.getBatsman());
    if (PlayerHelper.isSameInput(game)) {
      // OUT
      PlayerHelper.setBatsmanOut(game);
      PlayerHelper.reverseRoles(game);
    } else {
      // NOT OUT
      PlayerHelper.addRuns(game);
    }
    GamePlayHelper.setGameStatus(game);
    PlayerHelper.clearInputs(game);
    return game;
  }

  public Game restartGame(String gameId) {
    Game game = gameDB.getGame(gameId);
    game.setGameStatus(GameStatus.IN_PROGRESS);
    PlayerHelper.resetPlayers(game);
    playerNotificationService.notifyGameRestart(gameId);
    publishGame(game);
    return game;
  }

  public void publishGame(Game game) {
    template.convertAndSend("/game/" + game.getId(), game);
  }

  public void pauseCurrentPlayer(String gameId, String playerId) {
    template.convertAndSend("/game/" + gameId + "/player/" + playerId + "/pause", true);
  }

  public void unpauseOtherPlayers(String gameId, String playerId) {
    template.convertAndSend(
        "/game/" + gameId + "/player/" + PlayerHelper.getPlayer(gameId, playerId).getId() + "/pause",
        false);
    template.convertAndSend(
        "/game/" + gameId + "/player/" + PlayerHelper.getOtherPlayer(gameId, playerId).getId() + "/pause",
        false);
  }

  public void runPlay(String gameId, String playerId) {
    play(gameId);
    publishGame(gameDB.getGame(gameId));
    playerNotificationService.notifyResult(gameId, playerId);
    unpauseOtherPlayers(gameId, playerId);
  }

  public void holdPlay(String gameId, String playerId) {
    pauseCurrentPlayer(gameId, playerId);
    playerNotificationService.notifyPlayer(gameId, playerId,
        PlayerNotificationHelper.getPlayMsgForCurrentPlayer(gameId, playerId));
    playerNotificationService.notifyPlayer(gameId,
        PlayerHelper.getOtherPlayer(gameId, playerId).getId(),
        PlayerNotificationHelper.getPlayMsgForCurrentPlayer(gameId, playerId));
  }

  public void gamePlay(String gameId, String playerId, Integer input) {
    PlayerHelper.setInput(gameId, playerId, input);
    if (PlayerHelper.bothPlayersPlayed(gameId)) {
      if (PlayerHelper.isSameInput(gameDB.getGame(gameId)))
        playerNotificationService.alertPlayers(gameId, PlayerNotificationHelper.getOutMsg(gameId, playerId));
      runPlay(gameId, playerId);
    }
    else
      holdPlay(gameId, playerId);
  }

}
