package com.project.handcricket.player.controllers;

import com.project.handcricket.models.Player;
import com.project.handcricket.player.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @RequestMapping(value = "/newPlayer", method = RequestMethod.POST)
  public Player newPlayer(@RequestBody Player player) {
    return playerService.newPlayer(player);
  }

}
