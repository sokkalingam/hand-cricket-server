package com.project.handcricket.mongodb;

import com.project.handcricket.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopPlayerRepository extends MongoRepository<Player, String> {

  Player findTopByOrderByRunsDesc();

  Player findTopByOrderByWinsDesc();

}
