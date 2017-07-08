package com.project.handcricket.models;

public class GameView {

  private String gameId;
  private String batsmanName;
  private String bowlerName;

  public GameView(String gameId, String batsmanName, String bowlerName) {
    this.gameId = gameId;
    this.batsmanName = batsmanName;
    this.bowlerName = bowlerName;
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
}
