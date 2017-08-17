package com.project.handcricket.model;

import com.project.handcricket.enums.UpdateType;

public class Update {

  private UpdateType type;
  private String message;

  public UpdateType getType() {
    return type;
  }

  public void setType(UpdateType type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
