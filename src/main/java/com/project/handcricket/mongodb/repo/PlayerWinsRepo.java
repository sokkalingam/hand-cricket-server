package com.project.handcricket.mongodb.repo;

import com.project.handcricket.mongodb.model.PlayerWin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerWinsRepo extends MongoRepository<PlayerWin, String> {

  PlayerWin findTopByOrderByWinsDesc();

}
