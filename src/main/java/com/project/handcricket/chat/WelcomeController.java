package com.project.handcricket.chat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @RequestMapping("/")
  public String welcome() {
    return "Welcome! Spring Boot Server is Running..";
  }

  @RequestMapping("/keep-awake")
  public String keepAwake() {
    return "Thank you for the wake up call, I am awake!";
  }
}
