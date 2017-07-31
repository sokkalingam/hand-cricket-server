package com.project.handcricket.game.disconnect.services;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.game.play.GamePlayService;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.GameAndPlayer;
import com.project.handcricket.models.Player;
import com.project.handcricket.player.helpers.PlayerHelper;
import com.project.handcricket.player.helpers.PlayerNotificationHelper;
import com.project.handcricket.player.services.PlayerNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameDisconnectService {

  @Autowired
  private PlayerNotificationService playerNotificationService;

  @Autowired
  private GamePlayService gamePlayService;

  public void disconnect(String gameId, String playerId) {
    if (PlayerHelper.getPlayer(gameId, playerId) == null) return;
    playerNotificationService.alertPlayer(gameId, PlayerHelper.getOtherPlayer(gameId, playerId).getId(),
        PlayerNotificationHelper.getDisconnectedMsg(gameId, playerId));
  }

  public void disconnect(String sessionId) {
    GameAndPlayer gp = GameDB.getInstance().getSocketMap().get(sessionId);
    if (gp == null) return;
    disconnect(gp.getGameId(), gp.getPlayer().getId());
  }
}
