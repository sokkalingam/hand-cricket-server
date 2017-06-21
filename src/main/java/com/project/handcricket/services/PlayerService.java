package com.project.handcricket.services;

import com.project.handcricket.enums.PlayerStatus;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  @Autowired
  private GameService gameService;

  /**
   * Get Player
   * @param gameId
   * @param playerId
   * @return
   */
  public Player getPlayer(String gameId, String playerId) {
    Game game = gameService.getGame(gameId);
    if (game.getBatsman().getId().equals(playerId))
      return game.getBatsman();
    if (game.getBowler().getId().equals(playerId))
      return game.getBowler();
    return null;
  }

  /**
   * Get other player whose ID is different
   * @param gameId
   * @param playerId
   * @return
   */
  public Player getOtherPlayer(String gameId, String playerId) {
    Game game = gameService.getGame(gameId);
    if (!game.getBatsman().getId().equals(playerId))
      return game.getBatsman();
    if (!game.getBowler().getId().equals(playerId))
      return game.getBowler();
    return null;
  }

  /**
   * Set Last Delivery
   * @param gameId
   * @param playerId
   * @param input
   */
  public void setLastDelivery(String gameId, String playerId, Integer input) {
    Player player = getPlayer(gameId, playerId);
    if (player.getLastDelivery() == null)
      player.setLastDelivery(input);
  }

  /**
   * Check if batsman and bowler's last delivery was the same
   * @param game
   * @return
   */
  public boolean isSameDelivery(Game game) {
    return game.getBatsman().getLastDelivery().equals(game.getBowler().getLastDelivery());
  }

  /**
   * Set last deliveries to null
   * @param game
   */
  public void clearLastDeliveries(Game game) {
    game.getBatsman().setLastDelivery(null);
    game.getBowler().setLastDelivery(null);
  }

  /**
   * Check if both players have played
   * @param gameId
   * @return
   */
  public boolean bothPlayersPlayed(String gameId) {
    Game game = gameService.getGame(gameId);
    return game.getBatsman().getLastDelivery() != null
        && game.getBowler().getLastDelivery() != null;
  }

  /**
   * Add runs to batsman
   * @param game
   */
  public void addRuns(Game game) {
    Player batsman = game.getBatsman();
    Player bowler = game.getBowler();

    if (batsman.getLastDelivery() != 0) {
      batsman.setRuns(batsman.getRuns() + batsman.getLastDelivery());
    } else {
      batsman.setRuns(batsman.getRuns() + bowler.getLastDelivery());
    }
  }

  /**
   * Add balls to batsman
   * @param player
   */
  public void addBalls(Player player) {
    Integer balls = player.getBalls();
    player.setBalls(balls + 1);
  }

  /**
   * Set batsman to Out
   * @param game
   */
  public void setBatsmanOut(Game game) {
    game.getBatsman().setPlayerStatus(PlayerStatus.OUT);
  }

  /**
   * Reverse roles
   * Set Batsman to Bowler if not already bowled
   * Set Bowler to Batsman if not already batted
   * @param game
   */
  public void reverseRoles(Game game) {
    if (game.getBowler().getPlayerStatus() == PlayerStatus.OUT) return;
    Player temp = game.getBatsman();
    game.setBatsman(game.getBowler());
    game.setBowler(temp);
  }

}
