package com.project.handcricket.services;

import com.project.handcricket.helper.Helper;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

@Service
public class GameService {

  @Value("${gameTimeOutInHours}")
  private String gameTimeoutHr;

  public boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  private HashMap<String, Game> gameMap = new HashMap<>();

  public Game hostGame(Player player) {
    Game game = setupGame();
    game.setBatsman(player);
    return game;
  }

  public Game joinGame(String gameId, Player player) {
    Game game = gameMap.get(gameId);
    if (game == null) return null;
    game.touch();
    game.setBowler(player);
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

  public Game getGame(String gameId) {
    return gameMap.get(gameId);
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

  public Game addGame() {
    return setupGame();
  }

}
