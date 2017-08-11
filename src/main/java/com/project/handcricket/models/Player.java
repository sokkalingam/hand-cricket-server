package com.project.handcricket.models;

import com.project.handcricket.enums.PlayerStatus;
import com.project.handcricket.enums.PlayerType;
import com.project.handcricket.helper.Helper;

public class Player {

  private String id;
  private String name;
  private Integer runs;
  private Integer balls;
  private Integer lastDelivery;
  private Integer input;
  private PlayerStatus status;
  private PlayerType type;
  private Integer wins;

  public Player() {
    this.id = Helper.getRandomID(5);
    this.status = PlayerStatus.NotOut;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) { this.id = id; }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRuns() {
    return runs != null ? runs : 0;
  }

  public void setRuns(Integer runs) {
    this.runs = runs;
  }

  public Integer getBalls() {
    return balls != null ? balls : 0;
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

  public PlayerStatus getStatus() {
    return status;
  }

  public void setStatus(PlayerStatus status) {
    this.status = status;
  }

  public PlayerType getType() {
    return type;
  }

  public void setType(PlayerType type) {
    this.type = type;
  }

  public boolean isOut() {
    return status == PlayerStatus.Out;
  }

  public boolean isNotOut() {
    return status == PlayerStatus.NotOut;
  }

  public Integer getInput() {
    return input;
  }

  public void setInput(Integer input) {
    this.input = input;
  }

  public Integer getWins() {
    return wins != null ? wins : 0;
  }

  public void setWins(Integer wins) {
    this.wins = wins;
  }

}
