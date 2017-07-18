package com.project.handcricket.socket;

import com.project.handcricket.models.Player;
import com.project.handcricket.models.SocketConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = "prototype")
public class SocketService {

  private String connectionId;

  public String getConnectionId() {
    return connectionId;
  }

  public void setConnectionId(String connectionId) {
    this.connectionId = connectionId;
  }
}
