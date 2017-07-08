package com.project.handcricket.tasks;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  @Value("${baseUrl}")
  private String baseUrl;

}
