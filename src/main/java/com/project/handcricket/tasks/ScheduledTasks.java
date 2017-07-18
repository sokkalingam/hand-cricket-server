package com.project.handcricket.tasks;

import com.project.handcricket.game.manage.GameManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {

  @Value("${baseUrl}")
  private String baseUrl;

  @Autowired
  private GameManageService gameManageService;

  /**
   * Clear Inactive Games Every Hour
   */
  @Scheduled (fixedDelay = 60 * 60 * 1000)
  public void clearInactiveGame() {
    System.out.println("Clear Inactive Game: " + new Date());
    this.gameManageService.getActiveGames();
  }



}
