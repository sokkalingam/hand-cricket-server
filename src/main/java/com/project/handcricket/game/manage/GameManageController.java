package com.project.handcricket.game.manage;

import com.project.handcricket.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameManageController {

  @Autowired
  private GameManageService gameManageService;

  @RequestMapping("/activeGames")
  public Map<String, Game> getActiveGames() {
    return gameManageService.getActiveGames();
  }

}
