package com.project.handcricket.game.disconnect.services;

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

  public void disconnect(String gameId, String playerId) {
    playerNotificationService.alertPlayer(gameId, playerId, PlayerNotificationHelper.getDisconnectedMsg(gameId, playerId));
  }
}
