package com.project.handcricket.stats;

import com.project.handcricket.datarefresh.DataRefreshService;
import com.project.handcricket.model.Player;
import com.project.handcricket.mongodb.model.PlayerRun;
import com.project.handcricket.mongodb.model.PlayerWin;
import com.project.handcricket.mongodb.repo.PlayerRunsRepo;
import com.project.handcricket.mongodb.repo.PlayerWinsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    this.playerRunsRepoDeleteAll();
    this.playerWinsRepoDeleteAll();
  }

  public synchronized void playerRunsRepoDeleteAll() {
    this.playerRunsRepo.deleteAll();
  }

  public synchronized void playerWinsRepoDeleteAll() {
    this.playerWinsRepo.deleteAll();
  }

  public synchronized PlayerRun getPlayerWithMaxRuns() {
    return this.playerRunsRepo.findTopByOrderByRunsDesc();
  }

  public synchronized PlayerWin getPlayerWithMaxWins() {
    return this.playerWinsRepo.findTopByOrderByWinsDesc();
  }

  public synchronized void writePlayerWin(PlayerWin playerWin) {
    this.playerWinsRepo.save(playerWin);
  }

  public synchronized void writePlayerRun(PlayerRun playerRun) {
    this.playerRunsRepo.save(playerRun);
  }

  public boolean updateTopRunsPlayer(Player player) {
    PlayerRun playerWithMaxRuns = getPlayerWithMaxRuns();
    if (playerWithMaxRuns == null || playerWithMaxRuns.getRuns() == null || player.getRuns() > playerWithMaxRuns.getRuns()) {
      if (playerWithMaxRuns != null && playerWithMaxRuns.getRuns() != null && player.getRuns() > playerWithMaxRuns.getRuns())
        playerRunsRepoDeleteAll();
      writePlayerRun(new PlayerRun(player.getName(), player.getRuns()));
      return true;
    }
    return false;
  }

  public boolean updateTopWinsPlayer(Player player) {
    PlayerWin playerWithMaxWins = getPlayerWithMaxWins();
    if (playerWithMaxWins == null || playerWithMaxWins.getWins() == null || player.getWins() > playerWithMaxWins.getWins()) {
      if (playerWithMaxWins != null && playerWithMaxWins.getWins() != null && player.getWins() > playerWithMaxWins.getWins())
        playerWinsRepoDeleteAll();
      writePlayerWin(new PlayerWin(player.getName(), player.getWins()));
      return true;
    }
    return false;
  }

}
