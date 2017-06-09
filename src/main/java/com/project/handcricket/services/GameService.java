package com.project.handcricket.services;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class GameService {

  public boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  public String getGameId() {
    return UUID.randomUUID().toString();
  }


}
