package com.project.handcricket.game.setup;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.game.play.GamePlayService;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSetupService {

  private GameDB gameDB;
  private GamePlayService gamePlayService;

  @Autowired
  public GameSetupService(GamePlayService gamePlayService) {
    gameDB = GameDB.getInstance();
    this.gamePlayService = gamePlayService;
  }

  public Game getNewGame() {
    Game game = new Game();
    gameDB.addGame(game);
    return game;
  }

  public String hostGame(Player player) {
    Game game = getNewGame();
    game.setBatsman(player);
    return game.getId();
  }

  public Game joinGame(String gameId, Player player) {
    Game game = gameDB.getGame(gameId);
    if (game == null) return null;
    if (!isGameOpenToPlay(game)) return null;
    game.setBowler(player);
    game.setGameStatus(GameStatus.IN_PROGRESS);
    this.gamePlayService.publishGame(game);
    return game;
  }

  public boolean isGameOpenToPlay(Game game) {
    return game.getBowler() == null;
  }

}
