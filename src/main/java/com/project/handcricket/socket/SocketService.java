package com.project.handcricket.socket;

import com.project.handcricket.data.GameData;
import com.project.handcricket.model.GameAndPlayer;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

  public void storeSocketInfo(String socketId, String gameId, String playerId) {
    GameData.getInstance().getSocketMap().put(socketId, new GameAndPlayer(gameId, playerId));
  }

}
