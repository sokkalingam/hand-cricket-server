package com.project.handcricket.models;

import com.project.handcricket.enums.PlayerStatus;

import java.util.UUID;

public class Player {

  private String id;
  private String name;
  private Integer runs;
  private Integer balls;
  private Integer lastDelivery;
  private PlayerStatus playerStatus;

  public Player() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRuns() {
    return runs;
  }

  public void setRuns(Integer runs) {
    this.runs = runs;
  }

  public Integer getBalls() {
    return balls;
  }

  public void setBalls(Integer balls) {
    this.balls = balls;
  }

  public Integer getLastDelivery() {
    return lastDelivery;
  }

  public void setLastDelivery(Integer lastDelivery) {
    this.lastDelivery = lastDelivery;
  }

  public PlayerStatus getPlayerStatus() {
    return playerStatus;
  }

  public void setPlayerStatus(PlayerStatus playerStatus) {
    this.playerStatus = playerStatus;
  }

}
