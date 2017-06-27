package com.project.handcricket.services;

import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.enums.PlayerStatus;
import com.project.handcricket.helper.Helper;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

@Service
public class GameService {

  @Value("${gameTimeOutInHours}")
  private String gameTimeoutHr;

  @Autowired
  private PlayerService playerService;

  private HashMap<String, Game> gameMap = new HashMap<>();

  public boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  public Game getGame(String gameId) {
    return gameMap.get(gameId);
  }

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

  public Game addGame() {
    return setupGame();
  }

  public Game hostGame(Player player) {
    Game game = setupGame();
    game.setBatsman(player);
    return game;
  }

  public Game joinGame(String gameId, Player player) {
    Game game = gameMap.get(gameId.toUpperCase());
    if (game == null) return null;
    if (game.getBowler() != null) return null;
    game.touch();
    game.setBowler(player);
    game.setGameStatus(GameStatus.IN_PROGRESS);
    return game;
  }

  /**
   * Setup a new Game
   *
   * Set Random Game ID
   * Add it to the game Hash Map
   * Set last updated to now
   *
   * @return
   */
  public Game setupGame() {
    Game game = new Game();
    game.setId(Helper.getRandomID(5));
    gameMap.put(game.getId(), game);
    return game;
  }

  public Map<String, Game> getActiveGames() {
    Iterator<Game> iterator = gameMap.values().iterator();
    while(iterator.hasNext()) {
      Game game = iterator.next();
      if (new DateTime(game.getLastUpdated()).compareTo(new DateTime().minusHours(Integer.parseInt(gameTimeoutHr))) < 0)
        iterator.remove();
    }
    return gameMap;
  }

  public void setGameStatus(Game game) {
    Player batsman = game.getBatsman();
    Player bowler = game.getBowler();

    // Both not out, game in progress
    if (batsman.isNotOut() && bowler.isNotOut()) {
      game.setGameStatus(GameStatus.IN_PROGRESS);
      return;
    }

    // bowler is out, batsman chasing
    if (bowler.isOut() && batsman.isNotOut()) {
      // batman wins
      if (batsman.getRuns() >  bowler.getRuns()) {
        batsman.setStatus(PlayerStatus.Won);
        bowler.setStatus(PlayerStatus.Lost);
        game.setGameStatus(GameStatus.GAME_OVER);
      }
    }

    // both are out
    if (batsman.isOut() && bowler.isOut()) {
      // bowler wins
      if (bowler.getRuns() > batsman.getRuns()) {
        bowler.setStatus(PlayerStatus.Won);
        batsman.setStatus(PlayerStatus.Lost);
        game.setGameStatus(GameStatus.GAME_OVER);
      }
      // draw
      if (bowler.getRuns().equals(batsman.getRuns())) {
        game.setGameStatus(GameStatus.DRAW);
      }
    }
  }

  public Game play(String gameId) {
    Game game = getGame(gameId);
    playerService.addBalls(game.getBatsman());
    if (playerService.isSameInput(game)) {
      // OUT
      playerService.setBatsmanOut(game);
      playerService.reverseRoles(game);
    } else {
      // NOT OUT
      playerService.addRuns(game);
    }
    setGameStatus(game);
    playerService.clearInputs(game);
    return game;
  }

  public Game restartGame(String gameId) {
    Game game = getGame(gameId);
    game.setGameStatus(GameStatus.IN_PROGRESS);
    playerService.resetPlayer(game.getBatsman());
    playerService.resetPlayer(game.getBowler());
    return game;
  }

}
