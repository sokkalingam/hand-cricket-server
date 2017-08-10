package com.project.handcricket.models;

import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.helper.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {

  private String id;
  private Date lastUpdated;
  private Integer targetScore;
  private GameStatus gameStatus;
  private Player batsman;
  private Player bowler;
  private boolean connected;

  public Game() {
    this.id = Helper.getRandomID(5);
    this.touch();
    this.gameStatus = GameStatus.NOT_STARTED;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getTargetScore() {
    return targetScore;
  }

  public void setTargetScore(Integer targetScore) {
    this.targetScore = targetScore;
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  public void setGameStatus(GameStatus gameStatus) {
    this.touch();
    this.gameStatus = gameStatus;
  }

  public Player getBatsman() {
    return batsman;
  }

  public void setBatsman(Player batsman) {
    this.touch();
    this.batsman = batsman;
  }

  public Player getBowler() {
    return bowler;
  }

  public void setBowler(Player bowler) {
    this.touch();
    this.bowler = bowler;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  /**
   * Set Last Updated to Now
   */
  public void touch() { this.lastUpdated = new Date(); }

  public boolean isConnected() {
    return connected;
  }

  public void setConnected(boolean connected) {
    this.connected = connected;
  }
}
