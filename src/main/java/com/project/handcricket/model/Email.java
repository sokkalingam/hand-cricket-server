package com.project.handcricket.model;

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
    StringBuilder sb = new StringBuilder();

    sb.append("Feedback: ").append(this.body);

    if (this.name != null)
      sb.append("\n\nFrom: ").append(this.name);

    if (this.email != null)
      sb.append("\nEmail: ").append(this.email);

    return sb.toString();
  }
}
