package com.project.handcricket.mongodb.model;

import com.project.handcricket.model.Player;
import org.springframework.data.annotation.Id;

public class PlayerWin extends Player {

  @Id
  private String _id;

  public PlayerWin(String name, Integer wins, String email) {
    setName(name);
    setWins(wins);
    setEmail(email);
  }

}
