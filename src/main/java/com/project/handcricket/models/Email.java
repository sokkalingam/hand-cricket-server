package com.project.handcricket.models;

public class Email {

  private String name;
  private String email;
  private String body;

  public Email() {
  }

  public Email(String name, String email, String body) {
    this.name = name;
    this.email = email;
    this.body = body;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "Feedback: " + this.body + "\n\nFrom: " + this.name + ", Email: " + this.email;
  }
}
