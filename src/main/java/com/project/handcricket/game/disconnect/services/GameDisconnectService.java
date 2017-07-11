package com.project.handcricket.game.disconnect.services;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.game.disconnect.helpers.GameDisconnectHelper;
import com.project.handcricket.game.play.GamePlayService;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.GameAndPlayer;
import com.project.handcricket.player.helpers.PlayerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDisconnectService {

  @Autowired
  private GamePlayService gamePlayService;

  public void disconnect(String gameId, String playerId) {
    if (PlayerHelper.getPlayer(gameId, playerId) == null) return;
    Game game = GameDB.getInstance().getGame(gameId);
    GameDisconnectHelper.processGameDisconnect(game);
    PlayerHelper.initPlayers(gameId);
    gamePlayService.publishGame(game);
    GameDB.getInstance().getGameMap().remove(gameId);
  }

  public void disconnect(String sessionId) {
    GameAndPlayer gp = GameDB.getInstance().getSocketMap().get(sessionId);
    if (gp == null) return;
    disconnect(gp.getGameId(), gp.getPlayerId());
  }
}
