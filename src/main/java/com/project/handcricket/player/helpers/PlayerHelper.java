package com.project.handcricket.player.helpers;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.enums.PlayerStatus;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;

public class PlayerHelper {

  public static Player getPlayer(String gameId, String playerId) {
    Game game = GameDB.getInstance().getGame(gameId);
    if (game == null) return null;
    if (game.getBatsman().getId().equals(playerId))
      return game.getBatsman();
    else
      return game.getBowler();
  }

  public static Player getOtherPlayer(String gameId, String playerId) {
    Game game = GameDB.getInstance().getGame(gameId);
    if (!game.getBatsman().getId().equals(playerId))
      return game.getBatsman();
    else
      return game.getBowler();
  }

  /**
   * Set Last Delivery
   * @param gameId
   * @param playerId
   * @param input
   */
  public static void setInput(String gameId, String playerId, Integer input) {
    Player player = PlayerHelper.getPlayer(gameId, playerId);
    if (player.getInput() == null)
      player.setInput(input);
  }

  /**
   * Check if batsman and bowler's last delivery was the same
   * @param game
   * @return
   */
  public static boolean isSameInput(Game game) {
    return game.getBatsman().getInput().equals(game.getBowler().getInput());
  }

  /**
   * Set last deliveries to null
   * @param game
   */
  public static void clearInputs(Game game) {
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
  public static boolean bothPlayersPlayed(String gameId) {
    Game game = GameDB.getInstance().getGame(gameId);
    return game.getBatsman().getInput() != null
        && game.getBowler().getInput() != null;
  }

  /**
   * Add runs to batsman
   * @param game
   */
  public static void addRuns(Game game) {
    Player batsman = game.getBatsman();
    Player bowler = game.getBowler();
    if (batsman.getInput() == 0)
      batsman.setRuns(batsman.getRuns() + bowler.getInput());
    else
      batsman.setRuns(batsman.getRuns() + batsman.getInput());
  }

  /**
   * Add balls to batsman
   * @param player
   */
  public static void addBalls(Player player) {
    Integer balls = player.getBalls();
    player.setBalls(balls + 1);
  }

  public static void addWin(Player player) {
    player.setWins(player.getWins() + 1);
  }

  /**
   * Set batsman to Out
   * @param game
   */
  public static void setBatsmanOut(Game game) {
    game.getBatsman().setStatus(PlayerStatus.Out);
  }

  /**
   * Reverse roles
   * Set Batsman to Bowler if not already bowled
   * Set Bowler to Batsman if not already batted
   * @param game
   */
  public static void reverseRoles(Game game) {
    if (game.getBowler().getStatus() == PlayerStatus.Out) return;
    Player temp = game.getBatsman();
    game.setBatsman(game.getBowler());
    game.setBowler(temp);
  }

  public static void resetPlayers(Game game) {
    resetPlayer(game.getBatsman());
    resetPlayer(game.getBowler());
  }

  /**
   * Reset Player Scores
   * @param player
   */
  public static void resetPlayer(Player player) {
    if (player == null) return;
    player.setStatus(PlayerStatus.NotOut);
    player.setInput(null);
    player.setLastDelivery(null);
    player.setBalls(null);
    player.setRuns(null);
  }

  public static void resetWins(Game game) {
    if (game == null) return;
    if (game.getBatsman() != null) game.getBatsman().setWins(null);
    if (game.getBowler() != null) game.getBowler().setWins(null);
  }

  public static void removePlayer(String gameId, String playerId) {
    Game game = GameDB.getInstance().getGame(gameId);
    if (game.getBatsman().getId().equals(playerId))
      game.setBatsman(null);
    else if (game.getBowler().getId().equals(playerId))
      game.setBowler(null);
  }

  public static void initPlayers(String gameId) {
    Game game = GameDB.getInstance().getGame(gameId);
    if (game == null) return;
    initPlayer(game.getBatsman());
    initPlayer(game.getBowler());
  }

  public static void initPlayer(Player player) {
    if (player == null) return;
    resetPlayer(player);
    player.setType(null);
  }

  public static Player newPlayer(Player player) {
    Player newPlayer = new Player();
    newPlayer.setName(player.getName());
    return newPlayer;
  }

}
