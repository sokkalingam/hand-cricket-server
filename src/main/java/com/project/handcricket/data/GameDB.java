package com.project.handcricket.data;

import com.project.handcricket.models.Game;
import com.project.handcricket.models.GameAndPlayer;

import java.util.HashMap;
import java.util.Map;

public class GameDB {

  private static GameDB instance = null;

  private Map<String, Game> gameMap;
  private Map<String, GameAndPlayer> socketMap;

  private GameDB() {
    gameMap = new HashMap<String, Game>();
    socketMap = new HashMap<String, GameAndPlayer>();
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

  public Map<String, Game> getGameMap() {
    return gameMap;
  }

  public Map<String, GameAndPlayer> getSocketMap() {
    System.out.println(socketMap);
    return socketMap;
  }
}
