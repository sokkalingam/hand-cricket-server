package com.project.handcricket.game.manage;

import com.project.handcricket.data.GameData;
import com.project.handcricket.model.Game;
import com.project.handcricket.model.GameView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class GameManageService {

  public List<GameView> getGames() {
    Map<String, Game> gameMap = GameData.getInstance().getGameMap();
    List<GameView> games = new ArrayList<GameView>();

    Iterator<Game> iterator = gameMap.values().iterator();
    while(iterator.hasNext()) {
      Game game = iterator.next();
      games.add(new GameView(game));
    }

    return games;
  }

}
