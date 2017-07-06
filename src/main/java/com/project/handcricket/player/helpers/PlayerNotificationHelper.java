package com.project.handcricket.player.helpers;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;

public class PlayerNotificationHelper {

  public static String getPlayMsgForCurrentPlayer(String gameId, String playerId) {
    return "You have played " + PlayerHelper.getPlayer(gameId, playerId).getInput() +
        ", waiting for " + PlayerHelper.getOtherPlayer(gameId, playerId).getName() + " to play";
  }

  public static String playMsgForOtherPlayer(String gameId, String playerId) {
    return PlayerHelper.getPlayer(gameId, playerId).getName() + " has played, waiting for you to play";
  }

  public static String getResultForPlayer(String gameId, String playerId) {
    return "You played " + PlayerHelper.getPlayer(gameId, playerId).getLastDelivery() +
        ", " + PlayerHelper.getOtherPlayer(gameId, playerId).getName() + " played " +
        PlayerHelper.getOtherPlayer(gameId, playerId).getLastDelivery();
  }

  public static String getOutMsg(String gameId, String playerId) {
    Player currentPlayer = PlayerHelper.getPlayer(gameId, playerId);
    Player otherPlayer = PlayerHelper.getOtherPlayer(gameId, playerId);
    Game game = GameDB.getInstance().getGame(gameId);
    if (currentPlayer.getId().equals(game.getBatsman().getId()))
      return "You got OUT";
    else
      return otherPlayer.getName() + " got OUT";
  }

}
