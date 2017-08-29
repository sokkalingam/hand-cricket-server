package com.project.handcricket.mongodb.model;

import com.project.handcricket.model.Player;
import org.springframework.data.annotation.Id;

public class PlayerRun extends Player {

  @Id
  private String _id;

  public PlayerRun(String name, Integer runs) {
    setName(name);
    setRuns(runs);
  }

}
