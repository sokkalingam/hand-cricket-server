package com.project.handcricket.mongodb.model;

import java.util.Date;

public class DataRefresh {

  private Date playerStatsLastRefreshed;

  public DataRefresh() {
    this.playerStatsLastRefreshed = new Date();
  }

  public Date getPlayerStatsLastRefreshed() {
    return playerStatsLastRefreshed;
  }

  public void setPlayerStatsLastRefreshed(Date playerStatsLastRefreshed) {
    this.playerStatsLastRefreshed = playerStatsLastRefreshed;
  }
}
