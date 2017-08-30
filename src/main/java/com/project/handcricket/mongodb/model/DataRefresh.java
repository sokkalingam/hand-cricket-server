package com.project.handcricket.mongodb.model;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

public class DataRefresh {

  @Id
  private String _id;

  private LocalDate playerStatsDate;

  public DataRefresh() {
    this.playerStatsDate = new LocalDate();
  }

  public LocalDate getPlayerStatsDate() {
    return playerStatsDate;
  }

  public void setPlayerStatsDate(LocalDate playerStatsDate) {
    this.playerStatsDate = playerStatsDate;
  }
}
