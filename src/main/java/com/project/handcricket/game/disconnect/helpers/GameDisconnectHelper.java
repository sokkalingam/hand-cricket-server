package com.project.handcricket.game.disconnect.helpers;

import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.models.Game;

public class GameDisconnectHelper {

  public static void processGameDisconnect(Game game) {
    game.setConnected(false);
    game.setGameStatus(GameStatus.NOT_STARTED);
  }
}
