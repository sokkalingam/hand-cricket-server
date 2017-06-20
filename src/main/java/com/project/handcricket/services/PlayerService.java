package com.project.handcricket.services;

import com.project.handcricket.enums.PlayerStatus;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  public Player getPlayer(Game game, String playerId) {
    if (game.getBatsman().getId().equals(playerId))
      return game.getBatsman();
    if (game.getBowler().getId().equals(playerId))
      return game.getBowler();
    return null;
  }

  public Player getOtherPlayer(Game game, String playerId) {
    if (!game.getBatsman().getId().equals(playerId))
      return game.getBatsman();
    if (!game.getBowler().getId().equals(playerId))
      return game.getBowler();
    return null;
  }

  public void setLastDelivery(Game game, String playerId, Integer input) {
    Player player = getPlayer(game, playerId);
    player.setLastDelivery(input);
  }

  public boolean isSameDelivery(Game game) {
    return game.getBatsman().getLastDelivery().equals(game.getBowler().getLastDelivery());
  }

  public void clearLastDeliveries(Game game) {
    game.getBatsman().setLastDelivery(null);
    game.getBowler().setLastDelivery(null);
  }

  public boolean bothPlayersPlayed(Game game) {
    return game.getBatsman().getLastDelivery() != null
        && game.getBowler().getLastDelivery() != null;
  }

  public void addRuns(Game game) {
    Player batsman = game.getBatsman();
    Player bowler = game.getBowler();

    if (batsman.getLastDelivery() != 0) {
      batsman.setRuns(batsman.getRuns() + batsman.getLastDelivery());
    } else {
      batsman.setRuns(batsman.getRuns() + bowler.getLastDelivery());
    }
  }

  public void addBalls(Player player) {
    Integer balls = player.getBalls();
    player.setBalls(balls + 1);
  }

  public void setBatsmanOut(Game game) {
    game.getBatsman().setPlayerStatus(PlayerStatus.OUT);
  }

  public void reverseRoles(Game game) {
    Player temp = game.getBatsman();
    game.setBatsman(game.getBowler());
    game.setBowler(temp);
  }
}
