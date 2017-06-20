package com.project.handcricket.models;

import com.project.handcricket.enums.GameStatus;

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
  private List<Player> battedList = new ArrayList<>();
  private List<Player> bowledList = new ArrayList<>();
  private List<Update> updates = new ArrayList<>();

  public Game() {
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

  public List<Update> getUpdates() {
    return updates;
  }

  public void setUpdates(List<Update> updates) {
    this.updates = updates;
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

  public void addToBattedList(Player player) {
    this.battedList.add(player);
  }

  public void addToBowledList(Player player) {
    this.bowledList.add(player);
  }

  public boolean isBatted(Player player) {
    return _isPlayerInList(battedList, player);
  }

  public boolean isBowled(Player player) {
    return _isPlayerInList(bowledList, player);
  }

  private boolean _isPlayerInList(List<Player> players, Player player) {
    for (Player item : players)
      if (item.getId().equals(player.getId()))
        return true;
    return false;
  }
}
