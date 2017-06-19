package com.project.handcricket.services;

import com.project.handcricket.helper.Helper;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class GameService {

  public boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  private HashMap<String, Game> gameMap = new HashMap<>();
  private Game game = new Game();

  public Game hostGame(Player player) {
    Game game = setupGame();
    game.setBatsman(player);
    return game;
  }

  public Game joinGame(String gameId, Player player) {
    Game game = gameMap.get(gameId);
    if (game == null) return null;
    game.setBowler(player);
    return game;
  }

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
    return gameMap;
  }

}
