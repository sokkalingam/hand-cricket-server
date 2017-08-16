package com.project.handcricket.game.play;

import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.enums.PlayerStatus;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import com.project.handcricket.player.helpers.PlayerHelper;

public class GamePlayHelper {

  public static void setGameStatus(Game game) {
    Player batsman = game.getBatsman();
    Player bowler = game.getBowler();

    // Both not out, game in progress
    if (batsman.isNotOut() && bowler.isNotOut()) {
      game.setGameStatus(GameStatus.IN_PROGRESS);
      return;
    }

    // bowler is out, batsman chasing
    if (bowler.isOut() && batsman.isNotOut() && batsman.getRuns() >  bowler.getRuns()) {
        batsman.setStatus(PlayerStatus.Won);
        PlayerHelper.addWin(batsman);
        bowler.setStatus(PlayerStatus.Lost);
        game.setGameStatus(GameStatus.GAME_OVER);
    }

    // both are out
    if (batsman.isOut() && bowler.isOut()) {
      // bowler wins
      if (bowler.getRuns() > batsman.getRuns()) {
        bowler.setStatus(PlayerStatus.Won);
        PlayerHelper.addWin(bowler);
        batsman.setStatus(PlayerStatus.Lost);
        game.setGameStatus(GameStatus.GAME_OVER);
      }
      // draw
      if (bowler.getRuns().equals(batsman.getRuns())) {
        game.setGameStatus(GameStatus.DRAW);
      }
    }
  }

  /**
   * Set Target Score only if current batsman was first to bat
   * @param game
   */
  public static void setTargetScore(Game game) {
    if (game.getBowler().isNotOut())
      game.setTargetScore(game.getBatsman().getRuns());
  }
}
