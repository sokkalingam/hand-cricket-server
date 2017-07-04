package com.project.handcricket.models;

public class GameAndPlayer {

  private String gameId;
  private Player player;

  public GameAndPlayer() { }

  public GameAndPlayer(String gameId, Player player) {
    this.gameId = gameId;
    this.player = player;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
