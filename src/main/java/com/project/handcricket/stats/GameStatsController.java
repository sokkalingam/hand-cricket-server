package com.project.handcricket.stats;

import com.project.handcricket.helper.Helper;
import com.project.handcricket.model.Player;
import com.project.handcricket.mongodb.TopPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/stats")
public class GameStatsController {

  @Autowired
  private TopPlayerRepository topPlayerRepository;

  @RequestMapping("/getAll")
  public List<Player> getAll() {
    return topPlayerRepository.findAll();
  }

  @RequestMapping("/clearAll")
  public void clearAll() {
    topPlayerRepository.deleteAll();
  }

  @RequestMapping(value = "/submitScore", method = RequestMethod.POST)
  public void add(@RequestBody Player player) {
    topPlayerRepository.save(player);
  }

  @RequestMapping("/maxRuns")
  public Player maxRuns() {
    return topPlayerRepository.findTopByOrderByRunsDesc();
  }

  @RequestMapping("/maxWins")
  public Player maxWins() {
    return topPlayerRepository.findTopByOrderByWinsDesc();
  }


}
