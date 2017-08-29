package com.project.handcricket.data;

import com.project.handcricket.model.Game;
import com.project.handcricket.model.GameAndPlayer;

import java.util.HashMap;
import java.util.Map;

public class GameData {

  private static GameData instance = null;

  private Map<String, Game> gameMap;
  private Map<String, GameAndPlayer> socketMap;

  private GameData() {
    gameMap = new HashMap<String, Game>();
    socketMap = new HashMap<String, GameAndPlayer>();
  }

  public static GameData getInstance() {
    if (instance == null)
      instance = new GameData();
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
    return socketMap;
  }
}
