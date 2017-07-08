package com.project.handcricket.game.manage;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.models.Game;
import com.project.handcricket.models.GameView;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class GameManageService {

  public List<GameView> getGames() {
    Map<String, Game> gameMap = GameDB.getInstance().getGameMap();
    List<GameView> games = new ArrayList<GameView>();

    Iterator<Game> iterator = gameMap.values().iterator();
    while(iterator.hasNext()) {
      Game game = iterator.next();
      games.add(
          new GameView(
              game.getId(),
              game.getBatsman() != null ? game.getBatsman().getName() : null,
              game.getBowler() != null ? game.getBowler().getName() : null)
      );
    }

    return games;
  }

}
