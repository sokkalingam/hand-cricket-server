package com.project.handcricket.mongodb.repo;

import com.project.handcricket.mongodb.model.PlayerRun;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRunsRepo extends MongoRepository<PlayerRun, String> {

  PlayerRun findTopByOrderByRunsDesc();

}
