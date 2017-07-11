package com.project.handcricket.game.setup;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.game.play.GamePlayService;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import com.project.handcricket.player.helpers.PlayerHelper;
import com.project.handcricket.player.services.PlayerNotificationService;
import com.project.handcricket.socket.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameSetupService {

  private GameDB gameDB;
  private GamePlayService gamePlayService;
  private SimpMessagingTemplate template;
  private SocketService socketService;

  @Autowired
  public GameSetupService(GamePlayService gamePlayService,
                          PlayerNotificationService playerNotificationService,
                          SimpMessagingTemplate template,
                          SocketService socketService) {
    gameDB = GameDB.getInstance();
    this.gamePlayService = gamePlayService;
    this.template = template;
    this.socketService = socketService;
  }

  public Game getNewGame() {
    Game game = new Game();
    gameDB.addGame(game);
    return game;
  }

  public String hostGame(Player player) {
    Game game = getNewGame();
    game.setBatsman(player);
    return game.getId();
  }

  public Game joinGame(String gameId, Player player) {
    if (gameId == null) return null;
    Game game = gameDB.getGame(gameId.toUpperCase());
    if (game == null) return null;
    if (!isGameOpenToPlay(game)) return null;
    processJoin(game, player);
    this.gamePlayService.publishGame(game);
    return game;
  }

  public void processJoin(Game game, Player player) {
    game.setBowler(player);
    game.setGameStatus(GameStatus.IN_PROGRESS);
    game.setConnected(true);
    PlayerHelper.resetWins(game);
  }

  public boolean isGameOpenToPlay(Game game) {
    return game.getBowler() == null;
  }

  public void ping(String gameId, String playerId, String sessionId) {
    socketService.storeSocketInfo(sessionId, gameId, playerId);
    template.convertAndSend("/game/ping/" + gameId + "/" + playerId, "Ping received!");
  }

}
