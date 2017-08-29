package com.project.handcricket.stats;

import com.project.handcricket.model.Player;
import com.project.handcricket.mongodb.model.DataRefresh;
import com.project.handcricket.mongodb.model.PlayerRun;
import com.project.handcricket.mongodb.model.PlayerWin;
import com.project.handcricket.mongodb.repo.DataRefreshRepo;
import com.project.handcricket.mongodb.repo.PlayerRunsRepo;
import com.project.handcricket.mongodb.repo.PlayerWinsRepo;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlayerStatsService {

  private PlayerRunsRepo playerRunsRepo;
  private PlayerWinsRepo playerWinsRepo;
  private DataRefreshRepo dataRefreshRepo;

  @Autowired
  public PlayerStatsService(PlayerRunsRepo playerRunsRepo, PlayerWinsRepo playerWinsRepo,
                            DataRefreshRepo dataRefreshRepo) {
    this.playerRunsRepo = playerRunsRepo;
    this.playerWinsRepo = playerWinsRepo;
    this.dataRefreshRepo = dataRefreshRepo;
  }

  public void dataRefresh() {
    DataRefresh dataRefresh = new DataRefresh();
    List<DataRefresh> list = dataRefreshRepo.findAll();

    if (list != null && list.size() > 0) {
      dataRefresh = list.get(0);
      LocalDate timeNow = new DateTime().toLocalDate();
      LocalDate timeThen = new DateTime(dataRefresh.getPlayerStatsLastRefreshed()).toLocalDate();
      if(timeNow.compareTo(timeThen) > 0) {
        deleteAll();
        dataRefresh.setPlayerStatsLastRefreshed(timeNow.toDate());
        dataRefreshRepo.save(dataRefresh);
        dataRefreshRepo.delete(list.get(0));
      }
    } else {
      dataRefreshRepo.save(dataRefresh);
    }
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
