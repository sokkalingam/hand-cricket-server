package com.project.handcricket.stats;

import com.project.handcricket.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playerStats")
public class PlayerStatsController {

  @Autowired
  private PlayerStatsService playerStatsService;

  @RequestMapping("/clearAll")
  public void clearAll() {
    playerStatsService.deleteAll();
  }

  @RequestMapping(value = "/submit", method = RequestMethod.POST)
  public boolean add(@RequestBody Player player) {
    boolean runs = playerStatsService.updateTopRunsPlayer(player);
    boolean wins = playerStatsService.updateTopWinsPlayer(player);
    return runs || wins;
  }

  @RequestMapping("/maxRuns")
  public Player maxRuns() {
    playerStatsService.dataRefresh();
    return playerStatsService.getPlayerWithMaxRuns();
  }

  @RequestMapping("/maxWins")
  public Player maxWins() {
    playerStatsService.dataRefresh();
    return playerStatsService.getPlayerWithMaxWins();
  }

}
