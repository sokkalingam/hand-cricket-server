package com.project.handcricket.models;

import java.util.Date;

public class GameView {

  private String gameId;
  private String batsmanName;
  private String bowlerName;
  private String lastUpdated;

  public GameView(Game game) {
    if (game == null) return;
    this.gameId = game.getId();
    this.batsmanName = game.getBatsman() != null ? game.getBatsman().getName() : null;
    this.bowlerName = game.getBowler() != null ? game.getBowler().getName() : null;
    this.lastUpdated = game.getLastUpdated() != null ? game.getLastUpdated().toString() : null;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public String getBatsmanName() {
    return batsmanName;
  }

  public void setBatsmanName(String batsmanName) {
    this.batsmanName = batsmanName;
  }

  public String getBowlerName() {
    return bowlerName;
  }

  public void setBowlerName(String bowlerName) {
    this.bowlerName = bowlerName;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
