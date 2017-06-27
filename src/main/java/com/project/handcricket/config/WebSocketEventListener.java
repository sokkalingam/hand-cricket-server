package com.project.handcricket.config;

import com.project.handcricket.services.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener implements ApplicationListener<AbstractSubProtocolEvent> {

  @Autowired
  private SocketService socketService;

  @Override
  public void onApplicationEvent(AbstractSubProtocolEvent event) {
    if (event instanceof SessionConnectEvent) {

    } else if (event instanceof SessionConnectedEvent) {
      socketService.setConnectionId(event.getMessage().getHeaders().get("simpSessionId").toString());
    } else if (event instanceof SessionDisconnectEvent) {

    }
  }
}
