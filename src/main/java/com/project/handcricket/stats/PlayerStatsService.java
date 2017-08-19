package com.project.handcricket.stats;

import com.project.handcricket.datarefresh.DataRefreshService;
import com.project.handcricket.model.Player;
import com.project.handcricket.mongodb.model.DataRefresh;
import com.project.handcricket.mongodb.model.PlayerRun;
import com.project.handcricket.mongodb.model.PlayerWin;
import com.project.handcricket.mongodb.repo.DataRefreshRepo;
import com.project.handcricket.mongodb.repo.PlayerRunsRepo;
import com.project.handcricket.mongodb.repo.PlayerWinsRepo;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsService {

  private PlayerRunsRepo playerRunsRepo;
  private PlayerWinsRepo playerWinsRepo;

  private DataRefreshService dataRefreshService;

  @Autowired
  public PlayerStatsService(PlayerRunsRepo playerRunsRepo, PlayerWinsRepo playerWinsRepo,
                            DataRefreshService dataRefreshService) {
    this.playerRunsRepo = playerRunsRepo;
    this.playerWinsRepo = playerWinsRepo;
    this.dataRefreshService = dataRefreshService;
  }

  public void dataRefresh() {
    if (this.dataRefreshService.refreshData())
      deleteAll();
  }

  public void deleteAll() {
    this.playerRunsRepo.deleteAll();
    this.playerWinsRepo.deleteAll();
  }

  public PlayerRun getPlayerWithMaxRuns() {
    dataRefresh();
    return this.playerRunsRepo.findTopByOrderByRunsDesc();
  }

  public PlayerWin getPlayerWithMaxWins() {
    dataRefresh();
    return this.playerWinsRepo.findTopByOrderByWinsDesc();
  }

  public boolean updateTopRunsPlayer(Player player) {
    PlayerRun playerWithMaxRuns = getPlayerWithMaxRuns();
    if (playerWithMaxRuns == null || player.getRuns() > playerWithMaxRuns.getRuns()) {
      this.playerRunsRepo.save(new PlayerRun(player.getName(), player.getRuns()));
      if (playerWithMaxRuns != null)
        this.playerRunsRepo.delete(playerWithMaxRuns);
      return true;
    }
    return false;
  }

  public boolean updateTopWinsPlayer(Player player) {
    PlayerWin playerWithMaxWins = getPlayerWithMaxWins();
    if (playerWithMaxWins == null || player.getWins() > playerWithMaxWins.getWins()) {
      this.playerWinsRepo.save(new PlayerWin(player.getName(), player.getWins()));
      if (playerWithMaxWins != null)
        this.playerWinsRepo.delete(playerWithMaxWins);
      return true;
    }
    return false;
  }

}
