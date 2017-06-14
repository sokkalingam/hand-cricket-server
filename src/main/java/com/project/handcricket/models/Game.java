package com.project.handcricket.models;

import com.project.handcricket.enums.GameStatus;

import java.util.List;

public class Game {

  private String id;
  private Integer targetScore;
  private GameStatus gameStatus;
  private Player batsman;
  private Player bowler;
  private List<Player> battedList;
  private List<Player> bowledList;

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
    this.gameStatus = gameStatus;
  }

  public Player getBatsman() {
    return batsman;
  }

  public void setBatsman(Player batsman) {
    this.batsman = batsman;
  }

  public Player getBowler() {
    return bowler;
  }

  public void setBowler(Player bowler) {
    this.bowler = bowler;
  }

  public List<Player> getBattedList() {
    return battedList;
  }

  public void setBattedList(List<Player> battedList) {
    this.battedList = battedList;
  }

  public List<Player> getBowledList() {
    return bowledList;
  }

  public void setBowledList(List<Player> bowledList) {
    this.bowledList = bowledList;
  }

}
