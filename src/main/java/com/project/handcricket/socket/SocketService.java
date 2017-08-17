package com.project.handcricket.socket;

import com.project.handcricket.data.GameDB;
import com.project.handcricket.models.GameAndPlayer;
import com.project.handcricket.models.Player;
import com.project.handcricket.models.SocketConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocketService {

  public void storeSocketInfo(String socketId, String gameId, String playerId) {
    GameDB.getInstance().getSocketMap().put(socketId, new GameAndPlayer(gameId, playerId));
  }

}
