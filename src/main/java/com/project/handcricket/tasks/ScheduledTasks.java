package com.project.handcricket.tasks;

import com.project.handcricket.services.GameService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class ScheduledTasks {

  @Value("${baseUrl}")
  private String baseUrl;

  @Autowired
  private GameService gameService;

  /**
   * Clear Inactive Games Every Hour
   */
  @Scheduled (fixedDelay = 60 * 60 * 1000)
  public void clearInactiveGame() {
    System.out.println("Clear Inactive Game: " + new Date());
    this.gameService.getActiveGames();
  }

  /**
   * Pings this application every 5 min
   * to keep Heroku hosting alive
   *
   * Bedtime is from 12 AM to 7 AM everyday
   */
  @Scheduled (fixedDelay = 5 * 60 * 1000)
  public void pinger() {
    DateTime dateTime = new DateTime();
    if (dateTime.getHourOfDay() >= 0  && dateTime.getHourOfDay() <= 6)
      return;
    System.out.println("Pinger: " + new Date());
    new RestTemplate().getForObject(baseUrl + "/keep-awake", String.class);
  }

}
