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
  public void setInput(String gameId, String playerId, Integer input) {
    Player player = getPlayer(gameId, playerId);
    if (player.getInput() == null)
      player.setInput(input);
  }

  /**
   * Check if batsman and bowler's last delivery was the same
   * @param game
   * @return
   */
  public boolean isSameInput(Game game) {
    return game.getBatsman().getInput().equals(game.getBowler().getInput());
  }

  /**
   * Set last deliveries to null
   * @param game
   */
  public void clearInputs(Game game) {
    game.getBatsman().setLastDelivery(game.getBatsman().getInput());
    game.getBowler().setLastDelivery(game.getBowler().getInput());
    game.getBatsman().setInput(null);
    game.getBowler().setInput(null);
  }

  /**
   * Check if both players have played
   * @param gameId
   * @return
   */
  public boolean bothPlayersPlayed(String gameId) {
    Game game = gameService.getGame(gameId);
    return game.getBatsman().getInput() != null
        && game.getBowler().getInput() != null;
  }

  /**
   * Add runs to batsman
   * @param game
   */
  public void addRuns(Game game) {
    Player batsman = game.getBatsman();
    Player bowler = game.getBowler();

    if (batsman.getInput() != 0) {
      batsman.setRuns(batsman.getRuns() + batsman.getInput());
    } else {
      batsman.setRuns(batsman.getRuns() + bowler.getInput());
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
    game.getBatsman().setStatus(PlayerStatus.Out);
  }

  /**
   * Reverse roles
   * Set Batsman to Bowler if not already bowled
   * Set Bowler to Batsman if not already batted
   * @param game
   */
  public void reverseRoles(Game game) {
    if (game.getBowler().getStatus() == PlayerStatus.Out) return;
    Player temp = game.getBatsman();
    game.setBatsman(game.getBowler());
    game.setBowler(temp);
  }

  public String getMessageForCurrentPlayer(String gameId, String playerId) {
    Player otherPlayer = getOtherPlayer(gameId, playerId);
    return "You have played, waiting for " + otherPlayer.getName() + " to play";
  }

  public String getMessageForOtherPlayer(String gameId, String playerId) {
    Player currentPlayer = getPlayer(gameId, playerId);
    return currentPlayer.getName() + " has played, waiting for you to play";
  }
}
