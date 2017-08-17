package com.project.handcricket.player.services;

import com.project.handcricket.model.Player;
import com.project.handcricket.player.helpers.PlayerHelper;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  public Player newPlayer(Player player) {
    return PlayerHelper.newPlayer(player);
  }

}
