package com.project.handcricket.data;

import com.project.handcricket.models.Game;

import java.util.HashMap;

public class GameDB {

  private static GameDB instance = null;

  private HashMap<String, Game> gameMap;

  private GameDB() {
    gameMap = new HashMap<String, Game>();
  }

  public static GameDB getInstance() {
    if (instance == null)
      instance = new GameDB();
    return instance;
  }

  public void addGame(Game game) {
    if (game == null) return;
    gameMap.put(game.getId(), game);
  }

  public Game getGame(String gameId) {
    return gameMap.get(gameId);
  }

  public HashMap<String, Game> getGameMap() {
    return gameMap;
  }
}
