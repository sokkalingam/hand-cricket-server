package com.project.handcricket.game.manage;

import com.project.handcricket.models.Game;
import com.project.handcricket.models.GameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameManageController {

  @Autowired
  private GameManageService gameManageService;

  @RequestMapping("/games")
  public List<GameView> getGames() {
    return gameManageService.getGames();
  }

}
