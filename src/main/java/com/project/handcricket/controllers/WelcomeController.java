package com.project.handcricket.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @RequestMapping("/")
  public String welcome() {
    return "Welcome! Spring Boot Server is Running..";
  }
}
