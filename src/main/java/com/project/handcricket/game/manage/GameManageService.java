package com.project.handcricket.game.manage;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.models.Game;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class GameManageService {

  private Integer gameTimeoutHr = 1;

  public Map<String, Game> getActiveGames() {
    Map<String, Game> gameMap = GameDB.getInstance().getGameMap();

    Iterator<Game> iterator = gameMap.values().iterator();
    while(iterator.hasNext()) {
      Game game = iterator.next();
      if (new DateTime(game.getLastUpdated()).compareTo(new DateTime().minusHours(gameTimeoutHr)) < 0)
        iterator.remove();
    }

    return gameMap;
  }

}
