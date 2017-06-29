package com.project.handcricket.game.setup;

import com.project.handcricket.models.Game;
import com.project.handcricket.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameSetupController {

  @Autowired
  private GameSetupService setupService;

  @RequestMapping("/addGame")
  public Game addGame() {
    return setupService.getNewGame();
  }

  @RequestMapping(value = "/hostGame", method = RequestMethod.POST)
  public String getGameId(@RequestBody Player player) {
    return setupService.hostGame(player);
  }

  @RequestMapping(value = "/joinGame/{gameId}", method = RequestMethod.POST)
  public Game joinGame(@PathVariable String gameId, @RequestBody Player player) {
    return setupService.joinGame(gameId, player);
  }

}
