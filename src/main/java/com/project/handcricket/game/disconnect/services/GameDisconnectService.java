package com.project.handcricket.game.disconnect.services;

import com.project.handcricket.data.GameData;
import com.project.handcricket.game.disconnect.helpers.GameDisconnectHelper;
import com.project.handcricket.game.play.GamePlayService;
import com.project.handcricket.model.Game;
import com.project.handcricket.model.GameAndPlayer;
import com.project.handcricket.player.helpers.PlayerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDisconnectService {

  @Autowired
  private GamePlayService gamePlayService;

  public void disconnect(String gameId, String playerId) {
    if (PlayerHelper.getPlayer(gameId, playerId) == null) return;
    Game game = GameData.getInstance().getGame(gameId);
    GameDisconnectHelper.processGameDisconnect(game);
    PlayerHelper.initPlayers(gameId);
    gamePlayService.publishGame(game);
    GameData.getInstance().getGameMap().remove(gameId);
  }

  public void disconnect(String sessionId) {
    GameAndPlayer gp = GameData.getInstance().getSocketMap().get(sessionId);
    if (gp == null) return;
    disconnect(gp.getGameId(), gp.getPlayerId());
  }
}
